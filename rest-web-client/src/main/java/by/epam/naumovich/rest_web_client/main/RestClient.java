package by.epam.naumovich.rest_web_client.main;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClient {

	private static final String PLAIN_SERVICE_BASE_URL = "http://localhost:8080/ticket-web-service/plain";
	private static final String XML_SERVICE_BASE_URL = "http://localhost:8080/ticket-web-service/xml";
	
	private static final String ALL_TICKETS_URL_PART = "/tickets";
	private static final String SINGLE_TICKET_URL_PART = "/ticket";
	private static final String TICKET_WITH_NUM_URL_PART = "/tickets/%s";
	
	private static final int TICKET_ID_TO_GET = 103;
	private static final int TICKET_ID_TO_DELETE = 124;
	

	public static void main(String[] args) {
		new RestClient().runClient();
	}
	
	private void runClient() {
		Client client = ClientBuilder.newClient();
		testService(client, XML_SERVICE_BASE_URL, MediaType.APPLICATION_XML);
		testService(client, PLAIN_SERVICE_BASE_URL, MediaType.TEXT_PLAIN);
	}
	
	private void testService(Client client, String baseUrl, String mediaType) {
		WebTarget target = client.target(baseUrl.concat(ALL_TICKETS_URL_PART));
		getAllTickets(target, mediaType);
		
		target = client.target(baseUrl.concat(String.format(TICKET_WITH_NUM_URL_PART, TICKET_ID_TO_GET)));
		getOneTicket(target, mediaType);
		
		if (baseUrl.equals(PLAIN_SERVICE_BASE_URL)) {
			target = client.target(baseUrl.concat(SINGLE_TICKET_URL_PART));
			postTicket(target, mediaType);
		}
		
		target = client.target(baseUrl.concat(String.format(TICKET_WITH_NUM_URL_PART, TICKET_ID_TO_DELETE)));
		deleteTicket(target, mediaType);
	}
	
	private void getAllTickets(WebTarget target, String mediaType) {
		Response response = target.request(mediaType).get();
		String stringResponse = target.request(mediaType).get(String.class);
		System.out.println("GET ALL TICKETS in \"" + mediaType + "\":\n" + response + "\n" + stringResponse);
	}

	private void getOneTicket(WebTarget target, String mediaType) {
		Response response = target.request(mediaType).get();
		String stringResponse = target.request(mediaType).get(String.class);
		System.out.println("GET ONE TICKET in \"" + mediaType + "\":\n" + response + "\n" + stringResponse);
	}

	private void postTicket(WebTarget target, String mediaType) {
		Form form = new Form();
		form.param("depCity", "mn");
		form.param("depCountry", "by");
		Response response = target.request(mediaType).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		System.out.println("POST TICKET in \"" + mediaType + "\":\n" + response);
	}

	private void deleteTicket(WebTarget target, String mediaType) {
		Response response = target.request(mediaType).delete();
		String stringResponse = target.request(mediaType).delete(String.class);
		System.out.println("DELETE TICKET in \"" + mediaType + "\":\n" + response + "\n" + stringResponse);
	}

}
