package test.gawekar.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class SimpleCamelFtpClient {
	public static void main(String args[]) throws Exception{
		DefaultCamelContext ctx = new DefaultCamelContext();
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
						//.setHeader(Exchange.HTTP_PATH, constant("/polopoly_fs/7.12424.1336145629!/slideshowimage/forest%203.jpeg_gen/derivatives/fullsize/forest%203.jpeg"))
						//.setHeader(Exchange.HTTP_PATH, constant("/nature/index.html"))
						//.setBody(constant(""))
						.to("http4://www.nature.com")
						//.convertBodyTo(String.class)
						//.to("file:target/nature");
						.setHeader(Exchange.FILE_NAME, constant("a.txt"))
						.to("ftp://gawekar@test-fs.nature.com/?password=gAw999em&binary=false");
		    }
		});
		
		try {
			ctx.start();
			ProducerTemplate template = ctx.createProducerTemplate();
			template.sendBody("direct:a","a");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
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
