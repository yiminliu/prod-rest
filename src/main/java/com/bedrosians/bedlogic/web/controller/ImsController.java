package com.bedrosians.bedlogic.web.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bedrosians.bedlogic.domain.ims.Ims;
import com.bedrosians.bedlogic.domain.ims.KeymarkVendor;
import com.bedrosians.bedlogic.domain.ims.enums.MaterialCategory;
import com.bedrosians.bedlogic.domain.ims.enums.MaterialClass;
import com.bedrosians.bedlogic.domain.ims.enums.MaterialStyle;
import com.bedrosians.bedlogic.domain.ims.enums.MaterialType;
import com.bedrosians.bedlogic.domain.ims.enums.OriginCountry;
import com.bedrosians.bedlogic.domain.ims.enums.ShadeVariation;
import com.bedrosians.bedlogic.domain.ims.enums.Body;
import com.bedrosians.bedlogic.domain.ims.enums.MpsCode;
import com.bedrosians.bedlogic.domain.ims.enums.Status;
import com.bedrosians.bedlogic.domain.ims.enums.Grade;
import com.bedrosians.bedlogic.domain.ims.enums.Color;
import com.bedrosians.bedlogic.domain.ims.enums.DesignLook;
import com.bedrosians.bedlogic.domain.ims.enums.DesignStyle;
import com.bedrosians.bedlogic.domain.ims.enums.SurfaceApplication;
import com.bedrosians.bedlogic.domain.ims.enums.SurfaceFinish;
import com.bedrosians.bedlogic.domain.ims.enums.SurfaceType;
import com.bedrosians.bedlogic.domain.ims.enums.TaxClass;
import com.bedrosians.bedlogic.domain.ims.enums.Edge;
import com.bedrosians.bedlogic.domain.ims.enums.PackageUnit;
import com.bedrosians.bedlogic.domain.ims.enums.Usage;
import com.bedrosians.bedlogic.exception.DataNotFoundException;
import com.bedrosians.bedlogic.exception.DataOperationException;
import com.bedrosians.bedlogic.exception.InputParamException;
import com.bedrosians.bedlogic.service.ims.ImsService;
import com.bedrosians.bedlogic.service.mvc.ims.ImsServiceMVC;
import com.bedrosians.bedlogic.util.enums.DBOperation;
import com.bedrosians.bedlogic.web.validator.ImsValidator;

@Controller
@Scope("session")
@RequestMapping("/ims")
@SessionAttributes({"aItem", "item"})

public class ImsController {

   @Autowired
   private ImsServiceMVC imsServiceMVC;
   
   @Autowired
   private ImsService imsService;
       
   @Autowired
   @Qualifier("imsValidator")
   private ImsValidator validator;
   
   private String dbOperation = "";
   
   /**
   * This method is used to show ims main page
   *
   * @return String the ims home page
   */
   @RequestMapping(value = {"/index", "/overview" }, method = RequestMethod.GET)
   public String showHomePage() {
      return "ims/index";
   }
   
   //////////////////Search items ///////////////////
   /**
    * This method is used to show item detail information for the given item code
    *
    * @return String the item detail page
    */
   @RequestMapping(value="getItemDetail/{itemCode}", method = RequestMethod.GET)
   public String getItemDetail(@PathVariable("itemCode") String itemCode, Model model){
	   dbOperation = "retrieve";
	   Ims item = null;
	   try{
		   item = imsServiceMVC.getItemByItemCode(itemCode);
	   }
	   catch(Exception e){
		  throw e;
	   }
	   if(item == null)
		  throw new DataNotFoundException(itemCode);
	   model.addAttribute("item", item);
	   return "ims/getItemDetailSuccess";
   }
   
   /**
    * This method is used to show the form to search items
    *
    * @return String the item search form
    */
    @RequestMapping(value = "/getItem", method = RequestMethod.GET)
    public String getItems(Model model) {
       dbOperation = "retrieve";
       model.addAttribute("item", new Ims());	
       return "ims/getItems";
    }
    
    /**
     * This method is used to process the item search based on input search criteria
     *
     * @return String the item search resulting page which shows all items match the search criteria
     */
    @RequestMapping(value="/getItems", method = RequestMethod.POST)
    public String getItems(@RequestParam LinkedHashMap<String, List<String>> allRequestParams, @ModelAttribute("item") Ims item, Model model, BindingResult result, SessionStatus status) {
	   List<Ims> items = null;
	   try{
		   items = imsServiceMVC.getItems(allRequestParams);
	   }
	   catch(DataNotFoundException e){
		   status.setComplete(); //finished the "item" SessionAttribute
    	   throw e;
	   }
	   catch(Exception e){
		   status.setComplete(); //finished the "item" SessionAttribute
    	   throw e;
	   }
	   if(items == null && item.getItemcode() != null)
		  throw new DataNotFoundException(item.getItemcode());
	   model.addAttribute("itemList", items);
	   status.setComplete(); //finish the "item" SessionAttribute
	   return "ims/getItemsSuccess";
    }
   
    
   ////////////////// Create item ///////////////////
   /**
    * This method is used to show the item creation menu
    *
    * @return String item creation menu page
    */
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_PURCHASER')")
    @RequestMapping(value = "/createItem_menu", method = RequestMethod.GET)
    public String createItemMenu(Model model) {
        return "ims/createItem_menu";
    }
    
   
   /**
   * This method is used to show the form to create an item
   *
   * @return String
   */
   @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_PURCHASER')")
   @RequestMapping(value = "/createItem_begin", method = RequestMethod.GET)
   public String createItem(Model model) {
	  dbOperation = "create";
      model.addAttribute("aItem", new Ims());
      return "ims/createItem_general";
   }
   
   //handle general Info
   @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_PURCHASER')")
   @RequestMapping(value = "/createItem_material", method = RequestMethod.POST)
   public String itemMaterialForm(@ModelAttribute("aItem") @Valid Ims item, Model model, BindingResult bindingResult, SessionStatus status) {
	   if("update".equalsIgnoreCase(dbOperation)){
		   validator.validateGeneralInfo(item, DBOperation.UPDATE, bindingResult);
		   if (bindingResult.hasErrors()) 
			  return "ims/updateItem_begin";
	   }
	   else{
		   if(item.getItemcode() != null)
			  item.setItemcode(item.getItemcode().toUpperCase()); 
	       validator.validateGeneralInfo(item, DBOperation.CREATE, bindingResult);
	       if (bindingResult.hasErrors()) 
              return "ims/createItem_general";
	   }
	   if (item != null){ 
    	   //item.setItemcode(item.getItemcode().toUpperCase());
           model.addAttribute("aItem", item);
       }    
   	   return "ims/createItem_material";
   
   }
   
   //handle material info
   @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_PURCHASER')")
   @RequestMapping(value = "/createItem_dimensions", method = RequestMethod.POST)
   public String itemDimensionForm(@ModelAttribute("aItem") Ims item, Model model, BindingResult bindingResult) {
	  validator.validateMaterialInfo(item, bindingResult);
	  if (bindingResult.hasErrors()) 
          return "ims/createItem_material"; 
      if (item != null) 
          model.addAttribute("aItem", item); 
      if(item != null && item.getMaterial() != null && item.getMaterial().getMaterialcategory().equalsIgnoreCase("tool"))
    	  return "ims/createItem_price";
      else
          return "ims/createItem_dimensions";
   }
  
   //handle dimension
   @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_PURCHASER')")
   @RequestMapping(value = "/createItem_price", method = RequestMethod.POST)
   public String itemPriceForm(@ModelAttribute("aItem") Ims item, Model model, BindingResult bindingResult) {
      //validator.validateMaterialInfo(item, bindingResult);
   	  //if (bindingResult.hasErrors()) {
       //   return "ims/createItem_dimensions";
      //}
   	  if (item != null) 
          model.addAttribute("aItem", item);
      return "ims/createItem_price";
   }
   
   //handle price
   @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_PURCHASER')")
   @RequestMapping(value = "/createItem_application", method = RequestMethod.POST)
   public String itemApplicationForm(@ModelAttribute("aItem") Ims item, Model model, BindingResult bindingResult) {
      if (item != null) {
    	  validator.validatePrice(item, bindingResult);
          if (bindingResult.hasErrors()) 
               return "ims/createItem_price";
          if (item != null) 
        	  model.addAttribute("aItem", item);
     	  return "ims/createItem_application";
      }
      return null;
   }
   
   //handle application
   @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_PURCHASER')")
   @RequestMapping(value = "/createItem_packageUnits", method = RequestMethod.POST)
   public String itemPackageUnitForm(@ModelAttribute("aItem") Ims item, Model model, BindingResult result) {
      if (item != null) 
          model.addAttribute("aItem", item);
      return "ims/createItem_packageUnits";
   }
   
   //handle packageUnits
   @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_PURCHASER')")
   @RequestMapping(value = "/createItem_vendor", method = RequestMethod.POST)
   public String itemVendorForm(@ModelAttribute("aItem") Ims item, Model model, BindingResult bindingResult) {
	   validator.validatePackageUnits(item, bindingResult);
 	   if (bindingResult.hasErrors()) {
           return "ims/createItem_packageUnits";
       }
 	   if (item != null) 
          model.addAttribute("aItem", item);
      return "ims/createItem_vendor";
   }
   
   //handle vendor
   @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_PURCHASER')")
   @RequestMapping(value = "/createItem_icon", method = RequestMethod.POST)
   public String itemIconForm(@ModelAttribute("aItem") Ims item, Model model, BindingResult bindingResult) {
      if (item != null) {
    	  validator.validateVendorInfo(item, bindingResult);
     	   if (bindingResult.hasErrors()) {
               return "ims/createItem_vendor";
           }
     	  if (item != null) 
             model.addAttribute("aItem", item);
     	     return "ims/createItem_icon";
      }
      return null;
   }
    /**
     * This method is used to process the ticket form to create a ticket
     *
     * @return ModelAndView
     */
     @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_PURCHASER')")
     @RequestMapping(value = "/createItem_test", method = RequestMethod.POST)
     public String itemTestSpecForm(@ModelAttribute("aItem") Ims item, Model model, BindingResult result) {
        if (item != null)
             model.addAttribute("aItem", item);
       	 return "ims/createItem_test";
     }
     
     @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_PURCHASER')")
     @RequestMapping(value = "/createItem_note", method = RequestMethod.POST)
     public String itemNoteForm(@ModelAttribute("aItem") Ims item, Model model, BindingResult bindingResult) {
        if (item != null) {
            validator.validateTestSpecification(item, bindingResult);
       	    if (bindingResult.hasErrors()) {
                return "ims/createItem_test";
            }
            model.addAttribute("aItem", item);
       	   return "ims/createItem_note";
        }
        return null;
     }
     
     /**
      * This method is used to process the item form to create/update an item
      *
      * @return String item creating/update success page
      */
      @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_PURCHASER')")
      @RequestMapping(value = "/createItem_final", method = RequestMethod.POST)
      public String postCreateItem(@ModelAttribute("aItem") Ims item, Model model, BindingResult bindingResult, SessionStatus status) {
          validator.validateBuyer(item, bindingResult);
          if (bindingResult.hasErrors()) {
              return "ims/createItem_note";
          }
   	      try {
              if("update".equalsIgnoreCase(dbOperation)){
       	         imsService.createOrUpdateItem(item, DBOperation.UPDATE);
           	     model.addAttribute("operation", "Updated");
              }
              else{
                 imsService.createOrUpdateItem(item, DBOperation.CREATE);
                 model.addAttribute("operation", "Created");
              }
              model.addAttribute("item", item);
              status.setComplete(); //finished the "aItem" SessionAttribute
          }
       	  catch (Exception e) {
             throw e;
          }
   	      dbOperation = "";
          return "ims/createItemSuccess";
     }
      
    ////////////////// Update Item ////////////////  
   /**
    * This method is used to show the form to update an item
    *
    * @return String
    */
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_PURCHASER')")
    @RequestMapping(value = "/updateItem_begin/{itemcode}", method = RequestMethod.GET)
    public String updateItem(@PathVariable("itemcode") String itemCode, Model model){
       Ims item = null;
       try{
    	   item = imsServiceMVC.getItemByItemCode(itemCode);
       }
       catch (Exception te) {
          throw te;
       }
       model.addAttribute("aItem", item);
       dbOperation = "update";
       return "ims/updateItem_begin";
    }
   
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_PURCHASER')")
    @RequestMapping(value = "/updateItemForm", method = RequestMethod.GET)
    public String updateItem(Model model){
       model.addAttribute("item", new Ims());
       dbOperation = "update";
       return "ims/updateItemForm";
    }
    
    /**
     * This method is used to process the ticket form to create a ticket
     *
     * @return ModelAndView
     */
     @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
     @RequestMapping(value = "/updateItem_begin", method = RequestMethod.GET)
     public String updateItemBegin(@RequestParam("itemcode") String itemCode, @ModelAttribute("item") Ims item, Model model, BindingResult bindingResult) {
    	 dbOperation = "update";
    	 validator.validateItemCode(itemCode, DBOperation.UPDATE, bindingResult);
          if (bindingResult.hasErrors()) {
              return "ims/updateItemForm";
          }
      	 Ims retrievedItem = null;
    	 try {
    		 retrievedItem = imsServiceMVC.getItemByItemCode(itemCode);
         }
      	 catch (Exception te) {
             throw te;
         }
    	 if (retrievedItem == null)
    		 throw new DataNotFoundException(itemCode);
    	 model.addAttribute("aItem", retrievedItem);
    	 model.addAttribute("dbOperation", "update");
         return "ims/updateItem_begin";
     }  
     
     /////////////////////// Clone an Item /////////////////////
     /**
      * This method is used to show the form to create an item
      *
      * @return String
      */
      @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_PURCHASER')")
      @RequestMapping(value = "/cloneItem/{itemcode}", method = RequestMethod.GET)
      public String cloneItem(@PathVariable("itemcode") String itemCode, Model model){
    	  dbOperation = "clone";
    	  Ims retrievedItem = null;
     	   try {
     		  retrievedItem = imsServiceMVC.getItemByItemCodeForClone(itemCode);
          }
     	  catch(DataNotFoundException dnfe){
     		  throw new InputParamException("No item with the Item Code " + itemCode + " found in database. You cannot clone an item with this item code.");
          }
          catch (Exception te) {
              throw te;
          }
     	   if (retrievedItem == null)
     		   throw new InputParamException("No item with the Item Code " + itemCode + " found in database. You cannot clone an item with this item code.");
     	   else
     		   model.addAttribute("item", retrievedItem);
          return "ims/cloneItem";
      }  
     
      @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_PURCHASER')")
      @RequestMapping(value = "/cloneItemForm", method = RequestMethod.GET)
      public String cloneItem(Model model){
    	  dbOperation = "clone";
    	  model.addAttribute("item", new Ims());
         return "ims/cloneItemForm";
      }
      
      /**
       * This method is used to process the ticket form to create a ticket
       *
       * @return ModelAndView
       */
       //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
       @RequestMapping(value = "/cloneItem", method = RequestMethod.GET)
       public String cloneItem(@ModelAttribute("item") Ims item, Model model, BindingResult bindingResult) {
    	   Ims retrievedItem = null;
      	   try {
      		  retrievedItem = imsServiceMVC.getItemByItemCodeForClone(item.getItemcode());
           }
      	   catch(DataNotFoundException dnfe){
    		  throw new InputParamException("No item with the Item Code " + item.getItemcode() + " found in database. You cannot clone an item with this item code.");
           }
           catch (Exception te) {
               throw te;
           }
      	   if (retrievedItem == null)
      		   throw new InputParamException("No item with the Item Code " + item.getItemcode() + " found in database. You cannot clone an item with this item code.");
      	   else
      		   model.addAttribute("item", retrievedItem);
           return "ims/cloneItem";
       }  
       
       @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_PURCHASER')")
       @RequestMapping(value = "/cloneItem", method = RequestMethod.POST)
       public String PostCloneItem(@ModelAttribute("item") Ims item, Model model, BindingResult bindingResult) {
    	  if(item != null){
    		  validator.validateItemCode(item.getItemcode(), DBOperation.CLONE, bindingResult);
         	  if (bindingResult.hasErrors()) {
                  return "ims/cloneItem";
         	  } 
    	      try {
                  imsServiceMVC.createItem(item, DBOperation.CLONE);
              }
              catch (Exception te) {
                 throw te;
              }
          	  model.addAttribute("item", item);
              model.addAttribute("itemCode", item.getItemcode());
      	      model.addAttribute("operation", "Created");
      	      dbOperation = "";
      	      return "ims/createItemSuccess";
    	  }
    	  else
    		 throw new InputParamException("Empty item cannot be cloned.");
      }  
       
     ////////////////////// Delete an Item /////////////////////  
     @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_PURCHASER')")
     @RequestMapping(value="deleteItem/{itemCode}", method = RequestMethod.GET)
     public String deleteItem(@PathVariable("itemCode") String itemCode, Model model){
    	 dbOperation = "delete";
    	 try{
  		   imsServiceMVC.deleteItemByItemCode(itemCode);
  	    }
  	    catch(Exception e){
  		   throw e;
  	    }
  	    model.addAttribute("itemCode", itemCode);
  	    model.addAttribute("operation", "Deleted");
 	    return "ims/success";
     }
     
     @RequestMapping(value="deleteItem", method = RequestMethod.GET)
     public String deleteItem(Model model){
  	   model.addAttribute("item", new Ims());
  	   return "ims/deleteItem";
     }
     
     @RequestMapping(value="deleteItem", method = RequestMethod.POST)
     public String deleteItem(@ModelAttribute("item") Ims item, Model model, BindingResult bindingResult) {
        validator.validateItemCode(item.getItemcode(), DBOperation.DELETE, bindingResult);
        if (bindingResult.hasErrors()) {
            return "ims/deleteItem";
        }
  	    try{
  		   imsServiceMVC.deleteItem(item);
  	   }
  	   catch(Exception e){
  		   e.printStackTrace();
  	   }
  	   model.addAttribute("itemCode", item.getItemcode());
  	   model.addAttribute("operation", "Deleted");
  	   dbOperation = "";
 	   return "ims/success";
     }
     
     @RequestMapping(value="deactivateItem", method = RequestMethod.GET)
     public String deactivateItem(Model model){
  	   model.addAttribute("item", new Ims());
  	   return "ims/deactivateItem";
     }
     
     @RequestMapping(value="deactivateItem", method = RequestMethod.POST)
     public String deactivateItem(@ModelAttribute("item") Ims item, Model model){
  	   try{
  		   imsServiceMVC.deactivateItem(item);
  	   }
  	   catch(Exception e){
  		   e.printStackTrace();
  	   }
  	   model.addAttribute("itemCode", item.getItemcode());
  	   model.addAttribute("operation", "Deactvated");
   	   return "ims/success";
     }
     
     //------------------- Model Attributes Initiation ------------------//
     
     @ModelAttribute("countryList")
     public void countries(Model model) {
    	 model.addAttribute("countryList", Arrays.asList(OriginCountry.values()));
      	 //return Arrays.asList(OriginCountry.values());
     }
     
     @ModelAttribute("colorList")
     public static List<Color> colorCategories() {
    	 return Arrays.asList(Color.values());
     }
     
     @ModelAttribute("shadeVariationList")
     public static List<ShadeVariation> shadeVariations() {
      	 return Arrays.asList(ShadeVariation.values());
     }
     
     @ModelAttribute
     public static void  materialProperties(Model model) {
      	 model.addAttribute("materialTypeList", Arrays.asList(MaterialType.values()));
    	 model.addAttribute("materialCategoryList", Arrays.asList(MaterialCategory.values()));
    	 model.addAttribute("materialStyleList", Arrays.asList(MaterialStyle.values()));
    	 model.addAttribute("materialClassList", Arrays.asList(MaterialClass.values()));
     }
      
     @ModelAttribute
     public static void  newFeatures(Model model) {
      	 model.addAttribute("mpsList", Arrays.asList(MpsCode.values()));
    	 model.addAttribute("statusList", Arrays.asList(Status.values()));
    	 model.addAttribute("gradeList", Arrays.asList(Grade.values()));
    	 model.addAttribute("edgeList", Arrays.asList(Edge.values()));
    	 model.addAttribute("bodyList", Arrays.asList(Body.values()));
    	 model.addAttribute("designLookList", Arrays.asList(DesignLook.values()));
    	 model.addAttribute("designStyleList", Arrays.asList(DesignStyle.values()));
    	 model.addAttribute("surfaceApplicationList", Arrays.asList(SurfaceApplication.values()));
    	 model.addAttribute("surfaceTypeList", Arrays.asList(SurfaceType.values()));
    	 model.addAttribute("surfaceFinishList", Arrays.asList(SurfaceFinish.values()));
    	 model.addAttribute("taxClassList", Arrays.asList(TaxClass.values()));    	
     }
     
     @ModelAttribute
     public static void  packageUnits(Model model) {
      	 model.addAttribute("packageUnitList", Arrays.asList(PackageUnit.values()));
    	 model.addAttribute("statusList", Arrays.asList(Status.values()));
    	 model.addAttribute("gradeList", Arrays.asList(Grade.values()));
    	 model.addAttribute("edgeList", Arrays.asList(Edge.values()));
    	 model.addAttribute("bodyList", Arrays.asList(Body.values()));
    	 model.addAttribute("designLookList", Arrays.asList(DesignLook.values()));
    	 model.addAttribute("designStyleList", Arrays.asList(DesignStyle.values()));
    	 model.addAttribute("surfaceApplicationList", Arrays.asList(SurfaceApplication.values()));
    	 model.addAttribute("surfaceTypeList", Arrays.asList(SurfaceType.values()));
    	 model.addAttribute("surfaceFinishList", Arrays.asList(SurfaceFinish.values()));
    	 model.addAttribute("taxClassList", Arrays.asList(TaxClass.values()));    	
     }
     
     @ModelAttribute("usageList")
     public void usageList(Model model) {
    	 model.addAttribute("usageList", Arrays.asList(Usage.values()));
     }
     
     @ModelAttribute
     public void newVendorSystemEmptyList(Model model) {
      	 //model.addAttribute("newVendorSystem", new ArrayList());
    	 //imsServiceMVC.initVendors(3);
    	 model.addAttribute("newVendorSystem", imsServiceMVC.getNewVendorSystem());
     }
     
     
     //----------------------- Exception Handlers ------------------------//
     
     @ExceptionHandler(DataNotFoundException.class)
     public ModelAndView handleDataNotFoundException(DataNotFoundException ex) {
   
  		ModelAndView model = new ModelAndView("/exception/exception");
  		model.addObject("errorCode", ex.getErrorCode());
  		model.addObject("errorMessage", ex.getErrorMessage());
  		model.addObject("error", ex.getError());
   
  		return model;
   	 }
     
     @ExceptionHandler(InputParamException.class)
     public ModelAndView handleInputParamException(InputParamException ex) {
   
  		ModelAndView model = new ModelAndView("/exception/exception");
  		model.addObject("errorCode", ex.getErrorCode());
  		model.addObject("errorMessage", ex.getErrorMessage());
  		model.addObject("error", ex.getError());
   
  		return model;
   	}
     
    @ExceptionHandler(DataOperationException.class)
 	public ModelAndView handleDataOperationException(DataOperationException ex) {
  
 		ModelAndView model = new ModelAndView("/exception/exception");
 		model.addObject("errorCode", ex.getErrorCode());
 		model.addObject("errorMessage", ex.getErrorMessage());
 		model.addObject("exception", ex);
 		model.addObject("rootError", ex.getError());
  
 		return model;
  	}
    
    /**
     * This method is used to show item detail information for the given item code
     *
     * @return String the item detail page
     */
    @RequestMapping(value="getKeymarkVendorDetail/{vendorNumber}", method = RequestMethod.GET)
    public String getKeymarkVendorDetail(@PathVariable("vendorNumber") String vendorNumber, Model model){
 	   KeymarkVendor  keymarkVendor = null;
 	   try{
 		  keymarkVendor = imsServiceMVC.getKeymarkVendorByVendorNumber(Integer.valueOf(vendorNumber));
 	   }
 	   catch(Exception e){
 		  throw e;
 	   }
 	   if(keymarkVendor == null)
 		  throw new DataNotFoundException(vendorNumber);
 	   model.addAttribute("kVendor", keymarkVendor);
 	   return "ims/getKeymarkVendorDetailSuccess";
    }

}
