package test.gawekar.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class SimpleCamelFtpClient {
	public static void main(String args[]) throws Exception{
		if (args.length != 1){
			System.out.println("error pattern should be ' ftp.username ftp.pattern a,b,c'");
			System.exit(0);
		}
		String confiFile = System.getProperty("config_file");
		
		DefaultCamelContext ctx = new DefaultCamelContext();
		PropertiesComponent pc = new PropertiesComponent();
		pc.setLocation("file:" + confiFile);
		ctx.addComponent("properties", pc);
		
		ctx.addRoutes(new RouteBuilder() {
		    public void configure() {
		   		 from("direct:a")
		   		 	.process(new Processor() {
						public void process(Exchange exchange) throws Exception {
							System.out.println(exchange.getIn().getBody());
						}
					})
		            .split(body(String.class).tokenize(",")).parallelProcessing().threads(2)
		                .to("direct:b");
		    }
		});
		
		ctx.addRoutes(new RouteBuilder() {
		    public void configure() {
		   		        from("direct:b")
						.setHeader(Exchange.HTTP_PATH, constant("/polopoly_fs/7.5072.1340638582!/image/1.10884.jpg_gen/derivatives/landscape_300/1.10884.jpg"))
						//.setHeader(Exchange.HTTP_PATH, constant("/nature/index.html"))
						//.setBody(constant(""))
		   		        .setHeader("newFileName",simple("${body}"))
						.to("http4://www.nature.com")
						.setHeader(Exchange.FILE_NAME, simple("${properties:test.fs.root}${header.newFileName}.gif"))
						//.to("ftp://{{test.ftp.user}}@test-fs.nature.com/?password={{test.ftp.password}}&binary=true");
						.to("ftp://{{test.ftp.user}}@test-fs.nature.com/?password={{test.ftp.password}}{{test.options}}");
						//.to("ftp://gawekar@test-fs.nature.com/?password=gAw999em&binary=true");
						//.to("ftp://npis@test-fs.nature.com/?password=FL500835e&binary=true");
//						.setHeader("CamelFileName",simple("${header.newFileName}.gif"))
//						.to("file:target/");
		    }
		});
		
		try {
			ctx.start();
			ProducerTemplate template = ctx.createProducerTemplate();
			template.sendBody("direct:a",args[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.clearProperty("test.ftp.user");
			System.clearProperty("test.ftp.password");
			ctx.shutdown();
		}
	}
	
	public static void main1(String[] args) { 
        CamelContext context = new DefaultCamelContext(); 

        try { 
            ProducerTemplate template = context.createProducerTemplate(); 
            context.start(); 
            
            Exchange exchange = template.request("http4://www.google.com/search", new Processor() { 
            public void process(Exchange exchange) throws Exception { 
            } 
            }); 

            if (null != exchange) { 
                Message out = exchange.getOut(); 
                int responseCode = out.getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class); 
                System.out.println("Response: " + String.valueOf(responseCode)); 
            } 
        } catch (Exception ex) { 
            //System.out.println("Exception: " + ex);
        	ex.printStackTrace();
        }finally{
        	try {
        		context.stop();
			} catch (Exception e) {
				throw new RuntimeException("error while stopping the context",e);
			}
        	
        }

        System.out.println("DONE!!"); 
    }
}
