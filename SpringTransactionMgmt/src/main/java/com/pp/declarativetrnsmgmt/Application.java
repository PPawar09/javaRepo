package com.pp.declarativetrnsmgmt;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pp.declarativetrnsmgmt.config.Config;
import com.pp.declarativetrnsmgmt.model.Item;
import com.pp.declarativetrnsmgmt.service.ItemService;

public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.register(Config.class);
		context.refresh();

		ItemService service = context.getBean(ItemService.class);

		System.out.println("----Available Items----");
		List<Item> items = service.getItems();
		items.forEach(i -> System.out.println(i));

		System.out.println();
		try {
			Item delItem = new Item();
			delItem.setItemId(6l);
			service.deleteItem(delItem);
			System.out.println("Item Successfully Deleted.");
		} catch (Exception ex) {
			System.out.println("Item was not deleted.");
			System.out.println("Transaction rolled back due to Exception.");
		}
		System.out.println();

		context.close();
	}

}
