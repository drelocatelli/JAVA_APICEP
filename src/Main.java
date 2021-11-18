import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Program {
	public static Scanner input = new Scanner(System.in);
	public static String cep;
	public static String webservice;

	public static void main(String[] args) {

		System.out.print("Digite o CEP: ");
		cep = input.nextLine();
		System.out.println("CEP BUSCADO: "+ cep);

		webservice = "https://viacep.com.br/ws/" + cep + "/json";

		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI(webservice))
					.GET()
					.build();

			HttpClient httpClient = HttpClient.newHttpClient();
			var data = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			JSONObject response = new JSONObject(data.body());

			System.out.printf(
					"Logradouro: %s \n"
							+ "Bairro: %s \n"
							+ "Localidade: %s-%s \n"
					, response.get("logradouro"), response.get("bairro"), response.get("localidade"), response.get("uf"));


		} catch(Exception e) {
			e.printStackTrace();
		}


	}

}
