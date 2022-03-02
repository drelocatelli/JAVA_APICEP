package cursoPOO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

import entities.Location;

public class Program {
	
	private static Scanner input = new Scanner(System.in);
	private static String cep;
	private static Location location;

	public static void main(String[] args) {
		
		while(true) {
			System.out.print("Digite o CEP (SAIR = X): ");
			cep = input.nextLine();
			if(cep.equalsIgnoreCase("x")) break;
			buscaCEP(cep);
			getCEPInfo();
		}
		
		
	}
	
	public static void getCEPInfo() {
		System.out.println("Informações do CEP buscado:");
		System.out.printf("Logradouro: %s \n", location.getLogradouro());
		System.out.printf("Bairro: %s \n", location.getBairro());
		System.out.printf("Localidade: %s \n", location.getLocalidade());
		System.out.printf("UF: %s \n", location.getUf());
		System.out.println();
	}
	
	public static void buscaCEP(String cep) {
		System.out.println("CEP BUSCADO: "+ cep);

		String webservice = "https://viacep.com.br/ws/" + cep + "/json";

		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI(webservice))
					.GET()
					.build();

			HttpClient httpClient = HttpClient.newHttpClient();
			var data = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			JSONObject response = new JSONObject(data.body());
			location = new Location(
					response.getString("logradouro"), 
					response.getString("bairro"), 
					response.getString("localidade"), 
					response.getString("uf")
			);

		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
}
