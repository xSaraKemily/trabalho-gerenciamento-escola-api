/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author brayanbertan
 */
@Path("hello-world")
public class HelloWorldResource {
    
    @GET
    public String helloWorldMessage(){
        return "Hello World Web Services em Java.";
    }
    
}
