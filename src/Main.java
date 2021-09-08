import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.*;

public class Main {
	public static Scanner input = new Scanner(System.in);
	public static String cep;
	public static String method = "GET";
	public static String webservice;

	public static void main(String[] args){
		System.out.print("Digite o CEP: ");
		cep = input.nextLine();
		System.out.println("CEP BUSCADO: "+ cep);
		
		webservice = "https://viacep.com.br/ws/" + cep + "/json";

			try {
	
	            // conexao
	            URL url = new URL(webservice);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod(method);
	            conn.setRequestProperty("Accept", "application/json");
	
	            if (conn.getResponseCode() != 200)
	                throw new RuntimeException("HTTP ERROR CODE " + conn.getResponseCode());
	
	            // Input
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            StringBuilder result = new StringBuilder();
	            String output;
	            
	            while((output = br.readLine()) != null){
	                result.append(output);
	            }
	            
	            JSONObject response = new JSONObject(result.toString());
	            
//	            System.out.println(response);
	            System.out.printf(
	            		"Logradouro: %s \n"
	            		+ "Bairro: %s \n"
	            		+ "Localidade: %s-%s \n"
	            		, response.get("logradouro"), response.get("bairro"), response.get("localidade"), response.get("uf"));


				conn.disconnect();
			
			}catch(Exception err) {
				err.printStackTrace();
			}
	}

}