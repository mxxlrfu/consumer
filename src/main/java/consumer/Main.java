package consumer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dataset.Dataset;
import newsletter.Newsletter;

import java.awt.*;
import java.io.File;

public class Main {

	public static void main(String[] args) throws Exception {
		JsonElement dataset = new Dataset().init();
		JsonObject content = dataset.getAsJsonObject();
		File file = new Newsletter().generate(content);
		Desktop.getDesktop().browse(file.toURI());
	}
}
