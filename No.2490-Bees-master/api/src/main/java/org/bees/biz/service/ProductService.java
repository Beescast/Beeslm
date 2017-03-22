package org.bees.biz.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bees.biz.persistence.manager.ProductManager;
import org.bees.biz.persistence.manager.UserManager;
import org.bees.biz.persistence.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	private ProductManager productManager;	
		
	@Autowired
	private UserManager userManager;
	
	

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(ProductService.class);

	public Product getInfoById(Integer id) {		
		return this.productManager.getInfoById(id);
	}

	public List<Product> getList(Byte position, Integer limit, Integer start) {
		return this.productManager.getList(position,start,limit);
	}

	public boolean add( String name, String pic, String href, Byte position) {

		return this.productManager.add(name,pic,href,position);
	}

	public boolean del(Integer id) {
		return this.productManager.del(id);
	}

	public boolean edit(Integer id,  String name, String pic, String href, Byte position) {
		return this.productManager.edit(id,name,pic,href,position);
	}

	public Integer getListCount(Byte position) {
		return this.productManager.getListCount(position);
	}

	
	
}