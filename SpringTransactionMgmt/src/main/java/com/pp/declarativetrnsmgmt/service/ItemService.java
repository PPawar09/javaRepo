package com.pp.declarativetrnsmgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pp.declarativetrnsmgmt.dao.ItemDao;
import com.pp.declarativetrnsmgmt.model.Item;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ItemService {

	@Autowired
	private ItemDao itemDao;

	@Transactional(readOnly = true)
	public List<Item> getItems() {
		return itemDao.getItems();
	}

	public void addItem(Item item) {
		itemDao.addItem(item);
	}

	public void updateItem(Item item) {
		itemDao.updateItem(item);
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteItem(Item item) throws Exception {
		itemDao.deleteItem(item);
	}

}
