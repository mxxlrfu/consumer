package newsletter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Newsletter {
	private static final Gson parse = new Gson();
	private static final String logo = "https://files.startupranking.com/startup/thumb/5610_86eb10c4345f570e9660759afa111efd70f751a0_faveeo_m.png";

	private static void createHtmlPage(JsonArray items, BufferedWriter bufferedWriter) throws IOException {
		createHtmlHeader(bufferedWriter);
		createNewsFromTemplate(items, bufferedWriter);
		createHtmlFooter(bufferedWriter);
	}

	private static void createNewsFromTemplate(JsonArray items, BufferedWriter bufferedWriter) throws IOException {
		for (JsonElement item : items) {
			JsonObject document = (JsonObject) item.getAsJsonObject().get("document");
			News news = parse.fromJson(document, News.class);

			String image = news.imageurl != null ? news.imageurl : logo;

			String template = "<table class='table'>" +
					"<tr><td class='background' style='background-image: url(" + image + ")'></td></tr>" +
					"<tr><td class='details'>" +
					"<a href='" + news.url + "' target='_blank'><h2>" + news.title + "</h2></a>" +
					"<p>" + news.automaticsummary.substring(0, 200) + "...</p>" +
					"<a href='" + news.url + "' target='_blank'>Read News</a>" +
					"</td></tr>" +
					"</table>";

			bufferedWriter.write(template);
		}
	}

	private static void createHtmlHeader(BufferedWriter bufferedWriter) throws IOException {
		bufferedWriter.write("<html>" +
				"<head><style type='text/css'>" +
				"#wrapper { width: 100%; max-width: 450px; text-align: center; margin: 32px auto; }" +
				".table { width: 100%; background-color: #FFF; margin-top: 16px; }" +
				".background { height: 100px; background-size: cover; background-position: 50%; }" +
				".details { padding: 16px; }" +
				"</style>" +
				"<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
				"</head>" +
				"<body style='background-color: #e6e6e6; width: 100%; margin: 0;'>" +
				"<div id='wrapper'>" +
				"<h1>Top 10 Newsletter for you!</h1>");
	}

	private static void createHtmlFooter(BufferedWriter bufferedWriter) throws IOException {
		bufferedWriter.write("<br />" +
				"<a href='https://faveeo.com' target='_blank'>" +
				"<img src='" + logo + "' width='130' />" +
				"</a>" +
				"</div>" +
				"</body></html>");
		bufferedWriter.close();
	}

	public File generate(JsonObject content) throws IOException {
		File file = new File("./newsletter.html");
		JsonArray items = content.get("content").getAsJsonArray();
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

		createHtmlPage(items, bufferedWriter);

		return file;
	}

	public static void main(String[] args) {

	}
}

