package pt.exercise.Labseq.configuration.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.servers.ServerVariable;
import io.swagger.v3.oas.models.servers.ServerVariables;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();

        contact.setEmail("diogo.e.cruz@hotmail.com");
        contact.setName("Diogo Cruz");
        contact.setUrl("https://diogocruz.pt");

        Info info = new Info()
                .title("Labseq Sequence API")
                .version("1.0")
                .description("API for the Labseq Sequence service.")
                .contact(contact);

        ServerVariable serverVariableUrl = new ServerVariable();
        ServerVariable serverVariablePort = new ServerVariable();

        serverVariableUrl.setDefault("http://localhost");
        serverVariablePort.setDefault("8080");

        Server server = new Server();

        server.setUrl("{serverUrl}:{serverHttpPort}");
        server.setDescription("Descrição do Server");
        server.setVariables(new ServerVariables().addServerVariable("serverUrl", serverVariableUrl).addServerVariable("serverHttpPort", serverVariablePort));

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
