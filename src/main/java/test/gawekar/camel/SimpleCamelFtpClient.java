package test.gawekar.camel;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangeException;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.log4j.Logger;


public class SimpleCamelFtpClient {
	private static Logger LOG = Logger.getLogger(SimpleCamelFtpClient.class);
	
	private static class LocalExceptionBean{
		public void exceptionHandler(Exchange exchange, @ExchangeException Exception exception){
			System.out.println("exceptiion: ");
		}
	}
	
	public static void main(final String args[]) throws Exception{
		if (args.length != 2){
			System.out.println("error pattern should be '{sleeptimeInSec} a,b,c'");
			System.exit(0);
		}
		final String imageNames = args[1];
		String confiFile = System.getProperty("config_file");
		final  SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss:SS"); 
		DefaultCamelContext ctx = new DefaultCamelContext();
		PropertiesComponent pc = new PropertiesComponent();
		pc.setLocation("file:" + confiFile);
		ctx.addComponent("properties", pc);
		SimpleRegistry registry = new SimpleRegistry();
		ctx.setRegistry(registry);
		registry.put("exceptionBean", new LocalExceptionBean());
		
		ctx.addRoutes(new RouteBuilder(){
			@Override
			public void configure() throws Exception {
				from("timer://processingQueueReadyToDispatch?fixedRate=true&period={{npis.internal.dispatching.period}}")
				.setBody(constant("" + imageNames))
				.process(new Processor() {
							public void process(Exchange exchange) throws Exception {
								
								System.out.println("\n\n about to make ftp calls: " + format.format(new Date()));
							}
		   		        })
				.to("direct:a");
			}
		});
		
		ctx.addRoutes(new RouteBuilder() {
		    public void configure() {
		    	onException(Exception.class)
		    	.handled(true)
		    	.to("bean:exceptionBean");
		    	
		   		 from("direct:a")
		            .split(body(String.class).tokenize(",")).parallelProcessing().threads(2)
		                .to("direct:b");
		    }
		});
		
		ctx.addRoutes(new RouteBuilder() {
			//.setHeader(Exchange.HTTP_PATH, constant("/preview/polopoly_fs/7.12698!/image/A-M%20Android%20Head.GIF_gen/derivatives/fullsize/A-M%20Android%20Head.GIF"))
		    public void configure() {
		   		        from("direct:b")
		   		        .process(new Processor() {
							public void process(Exchange exchange) throws Exception {
								System.out.println(Thread.currentThread().getId() +   " processing ==>   " + exchange.getIn().getBody());
							}
		   		        })
		   		        //image pull & ftp
		   		        .setHeader(Exchange.HTTP_PATH, simple("${properties:test.src.data.for.image}"))
		   		        .setHeader("newFileName",simple("${body}"))
						.to("http4://{{test.src.hostname.for.image}}")
						.setHeader(Exchange.FILE_NAME, simple("${properties:test.fs.root}${header.newFileName}.gif"))
						.to("ftp://{{test.ftp.user}}@test-fs.nature.com/?password={{test.ftp.password}}{{test.options}}")
						//text pull & push
						.setBody(constant(""))
						.setHeader(Exchange.HTTP_PATH, simple("${properties:test.src.data.for.text}"))
						.to("http4://{{test.src.hostname.for.text}}")
						.setHeader(Exchange.FILE_NAME, simple("${properties:test.fs.root}${header.newFileName}.txt"))
						.to("ftp://{{test.ftp.user}}@test-fs.nature.com/?password={{test.ftp.password}}")
						.process(new Processor() {
							public void process(Exchange exchange) throws Exception {
//								System.out.println(Thread.currentThread().getId() +   " finished processing. Going to sleep for a while 50 secs ==>   " + exchange.getIn().getHeader("newFileName") + "  time: " + format.format(new Date()));
//								//throw new RuntimeException("Exception injected for thread: "  + Thread.currentThread().getId());
//								Thread.sleep(50000);
//								System.out.println(Thread.currentThread().getId() +   " woke up ==>   " + exchange.getIn().getHeader("newFileName") + "  time: " +  format.format(new Date()));
								System.out.println("no sleeping=====> " +  format.format(new Date()));
							}
		   		        });
//						.setHeader("CamelFileName",simple("${header.newFileName}.gif"))
//						.to("file:target/");
		    }
		});
		
		try {
			ctx.start();
			System.out.println("Going to sleep for in sec? " + args[0]);
			Thread.sleep(Integer.parseInt(args[0]) * 1000);
//			ProducerTemplate template = ctx.createProducerTemplate();
//			template.sendBody("direct:a",args[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ctx.shutdown();
		}
	}
}
