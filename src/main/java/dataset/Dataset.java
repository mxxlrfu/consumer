package dataset;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Dataset {
	private static final String documentUrl =
			"https://again.faveeo.com/horizons/v1/9f211683-ec2e-4701-b791-6303ac13c7a8/public/content" +
					"?boost_title=true&date_type=publication&from=d15&page=1&pagesize=10" +
					"&show_only_enhanced_content=true";

	private static InputStreamReader fetchDocument() throws IOException {
		URL url = new URL(documentUrl);
		URLConnection request = url.openConnection();
		request.connect();

		return new InputStreamReader((InputStream) url.getContent());
	}

	public JsonElement init() throws IOException {
		JsonElement document = JsonParser.parseReader(fetchDocument());
		return document;
	}

	public static void main(String[] args) {

	}
}
