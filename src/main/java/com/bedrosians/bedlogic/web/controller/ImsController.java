package com.bedrosians.bedlogic.web.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bedrosians.bedlogic.domain.ims.Ims;
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
import com.bedrosians.bedlogic.service.mvc.ims.ImsServiceMVC;
import com.bedrosians.bedlogic.web.validator.ImsValidator;

@Controller
@Scope("session")
@RequestMapping("/ims")
@SessionAttributes({"aItem", "item"})

public class ImsController {

   @Autowired
   private ImsServiceMVC imsServiceMVC;
       
   @Autowired
   @Qualifier("imsValidator")
   private ImsValidator validator;
   /**
   * This method is used to show ims main page
   *
   * @return String
   */
   @RequestMapping(value = { "", "/", "/imsHome", "/index", "/overview" }, method = RequestMethod.GET)
   public String showHomePage() {
      return "ims/index";
   }
   
   @RequestMapping(value="getItemDetail/{itemCode}", method = RequestMethod.GET)
   public String getItemByItemCode(@PathVariable("itemCode") String itemCode, Model model){
	   Ims item = null;
	   try{
		   item = imsServiceMVC.getItemByItemCode(itemCode);
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
	   model.addAttribute("item", item);
	   return "ims/getItemDetailSuccess";
   }
   
   /**
    * This method is used to show the form to create an item
    *
    * @return String
    */
    // @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @RequestMapping(value = "/getItem", method = RequestMethod.GET)
    public String getItems(Model model) {
       model.addAttribute("item", new Ims());	
       return "ims/getItems";
    }
    
   @RequestMapping(value="/getItems", method = RequestMethod.POST)
   public String getItems(@RequestParam LinkedHashMap<String, List<String>> allRequestParams, @ModelAttribute("item") Ims item, Model model, BindingResult result, SessionStatus status) {
	   List<Ims> items = null;
	   try{
		   items = imsServiceMVC.getItems(allRequestParams);
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
	   model.addAttribute("itemList", items);
	   status.setComplete(); //finished the "aItem" SessionAttribute
	   return "ims/getItemsSuccess";
   }
   
   /**
   * This method is used to show the form to create an item
   *
   * @return String
   */
   // @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
   @RequestMapping(value = "/createItem_begin", method = RequestMethod.GET)
   public String createItem(Model model) {
      model.addAttribute("aItem", new Ims());
      return "ims/createItem_general";
   }
   
   
   /**
   * This method is used to process the ticket form to create a ticket
   *
   * @return ModelAndView
   */
   //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
   /*@RequestMapping(value = "/createItem", method = RequestMethod.POST)
   public String createItem(@ModelAttribute("item") Ims item, Model model, BindingResult result) {
       String itemCode = null;
	   if (item != null) {
    	   try {
    		   itemCode = imsServiceMVC.createItem(item);
                model.addAttribute("item", item);
                model.addAttribute("itemCode", itemCode);
           }
    	   catch (Exception te) {
             return te.getMessage();
           }
           return "ims/createItemSuccess";
      }
      return null;
   }
*/
   //handle general Info
   @RequestMapping(value = "/createItem_material", method = RequestMethod.POST)
   public String itemMaterialForm(@ModelAttribute("aItem") @Valid Ims item, Model model, BindingResult bindingResult, SessionStatus status) {
	   //validator.validate(item, bindingResult);
	 //  validator.validateGeneralInfo(item, bindingResult);
	   if (bindingResult.hasErrors()) {
           //logger.info("Returning custSave.jsp page");
           return "ims/createItem_general";
       }
      if (item != null) {
    	   try {
             model.addAttribute("aItem", item);
         }
    	   catch (Exception te) {
             return te.getMessage();
         }
     	   return "ims/createItem_material";
      }
      return null;
   }
   
   
   @RequestMapping(value = "/createItem_dimension", method = RequestMethod.POST)
   public String itemDimensionForm(@ModelAttribute("aItem") Ims item, Model model, BindingResult result) {
      if (item != null) {
    	   try {
             model.addAttribute("aItem", item);
         }
    	   catch (Exception te) {
             return te.getMessage();
         }
     	   return "ims/createItem_dimension";
      }
      return null;
   }
  
   //handle material and dimension
   @RequestMapping(value = "/createItem_price", method = RequestMethod.POST)
   public String itemPriceForm(@ModelAttribute("aItem") Ims item, Model model, BindingResult bindingResult) {
      if (item != null) {
    	//  validator.validate(item, bindingResult);
   	   //if (bindingResult.hasErrors()) {
              //logger.info("Returning custSave.jsp page");
       //       return "ims/createItem_price";
       //   }
    	   try {
             model.addAttribute("aItem", item);
         }
    	   catch (Exception te) {
             return te.getMessage();
         }
     	   return "ims/createItem_price";
      }
      return null;
   }
   
   //handle price
   @RequestMapping(value = "/createItem_application", method = RequestMethod.POST)
   public String itemApplicationForm(@ModelAttribute("aItem") Ims item, Model model, BindingResult bindingResult) {
      if (item != null) {
    	  validator.validatePrice(item, bindingResult);
      	   if (bindingResult.hasErrors()) {
                 //logger.info("Returning custSave.jsp page");
                 return "ims/createItem_price";
             }
    	   try {
             model.addAttribute("aItem", item);
         }
    	   catch (Exception te) {
             return te.getMessage();
         }
     	   return "ims/createItem_application";
      }
      return null;
   }
   
   //handle application
   @RequestMapping(value = "/createItem_packageUnits", method = RequestMethod.POST)
   public String itemPackageUnitForm(@ModelAttribute("aItem") Ims item, Model model, BindingResult result) {
      if (item != null) {
    	   try {
             model.addAttribute("aItem", item);
         }
    	   catch (Exception te) {
             return te.getMessage();
         }
     	   return "ims/createItem_packageUnits";
      }
      return null;
   }
   
   @RequestMapping(value = "/createItem_vendor", method = RequestMethod.POST)
   public String itemVendorForm(@ModelAttribute("aItem") Ims item, Model model, BindingResult result) {
      if (item != null) {
    	   try {
             model.addAttribute("aItem", item);
         }
    	   catch (Exception te) {
             return te.getMessage();
         }
     	   return "ims/createItem_vendor";
      }
      return null;
   }
   
   //handle vendor
   @RequestMapping(value = "/createItem_icon", method = RequestMethod.POST)
   public String itemIconForm(@ModelAttribute("aItem") Ims item, Model model, BindingResult bindingResult) {
      if (item != null) {
    	  validator.validateVendorInfo(item, bindingResult);
     	   if (bindingResult.hasErrors()) {
                //logger.info("Returning custSave.jsp page");
                return "ims/createItem_vendor";
           }
     	   try {
             model.addAttribute("aItem", item);
         }
    	   catch (Exception te) {
             return te.getMessage();
         }
     	   return "ims/createItem_icon";
      }
      return null;
   }
    /**
     * This method is used to process the ticket form to create a ticket
     *
     * @return ModelAndView
     */
     //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
     @RequestMapping(value = "/createItem_test", method = RequestMethod.POST)
     public String itemTestingForm(@ModelAttribute("aItem") Ims item, Model model, BindingResult result) {
        if (item != null) {
      	   try {
               model.addAttribute("aItem", item);
           }
      	   catch (Exception te) {
               return te.getMessage();
           }
       	   return "ims/createItem_test";
        }
        return null;
     }
     
     @RequestMapping(value = "/createItem_note", method = RequestMethod.POST)
     public String itemNoteForm(@ModelAttribute("aItem") Ims item, Model model, BindingResult bindingResult) {
        if (item != null) {
        	 validator.validateTestSpecification(item, bindingResult);
       	   if (bindingResult.hasErrors()) {
                  //logger.info("Returning custSave.jsp page");
                  return "ims/createItem_test";
             }
       	   try {
               model.addAttribute("aItem", item);
           }
      	   catch (Exception te) {
               return te.getMessage();
           }
       	   return "ims/createItem_note";
        }
        return null;
     }
     
     /**
      * This method is used to process the ticket form to create a ticket
      *
      * @return ModelAndView
      */
      //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
      @RequestMapping(value = "/createItem_final", method = RequestMethod.POST)
      public String createItemIcons(@ModelAttribute("aItem") @Valid Ims item, Model model, BindingResult bindingResult, SessionStatus status) {
          String itemCode = null;
          if (bindingResult.hasErrors()) {
              //logger.info("Returning custSave.jsp page");
              return "createItem";
          }
   	      if (item != null) {
       	     try {
       		    itemCode = imsServiceMVC.createItem(item);
                model.addAttribute("item", item);
                model.addAttribute("itemCode", itemCode);
                status.setComplete(); //finished the "aItem" SessionAttribute
             }
       	     catch (Exception te) {
                 return te.getMessage();
             }
             return "ims/createItemSuccess";
         }
         return null;
      }
      
     /**
      * This method is used to process the ticket form to create a ticket
      *
      * @return ModelAndView
      */
      //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
      @RequestMapping(value = "/createItemFinalPage", method = RequestMethod.POST)
      public String createItemFinal(@ModelAttribute("item") Ims item, Model model, BindingResult result) {
          String itemCode = null;
   	   if (item != null) {
       	   try {
       		   itemCode = imsServiceMVC.createItem(item);
                   model.addAttribute("item", item);
                   model.addAttribute("itemCode", itemCode);
              }
       	   catch (Exception te) {
                return te.getMessage();
              }
              return "ims/createItemSuccess";
         }
         return null;
      }
      
   /**
    * This method is used to show the form to create an item
    *
    * @return String
    */
    // @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @RequestMapping(value = "/updateItem/{itemcode}", method = RequestMethod.GET)
    public String updateItem(@PathVariable("itemcode") String itemCode, Model model){
       Ims item = null;
       try{
    	   item = imsServiceMVC.getItemByItemCode(itemCode);
       }
       catch (Exception te) {
           return te.getMessage();
       }
       model.addAttribute("item", item);
       return "ims/updateItem";
    }
   
    @RequestMapping(value = "/updateItemInitForm", method = RequestMethod.GET)
    public String updateItem(Model model){
       model.addAttribute("item", new Ims());
       return "ims/updateItemForm";
    }
    
    /**
     * This method is used to process the ticket form to create a ticket
     *
     * @return ModelAndView
     */
     //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
     @RequestMapping(value = "/updateItem", method = RequestMethod.GET)
     public String updateItem(@ModelAttribute("item") Ims item, Model model, BindingResult result) {
      	 Ims retrievedItem = null;
    	 try {
    		 retrievedItem = imsServiceMVC.getItemByItemCode(item.getItemcode());
             model.addAttribute("item", retrievedItem);
         }
      	 catch (Exception te) {
             return te.getMessage();
         }
         return "ims/updateItem";
     }  
     
     @RequestMapping(value = "/updateItem", method = RequestMethod.POST)
     public String PostupdateItem(@ModelAttribute("item") Ims item, Model model, BindingResult result) {
      	 try {
              imsServiceMVC.updateItem(item);
              model.addAttribute("Item", item);
         }
      	 catch (Exception te) {
             return te.getMessage();
         }
         return "ims/updateItemSuccess";
     }  
     
     @RequestMapping(value="deleteItem/{itemCode}", method = RequestMethod.GET)
     public String deleteItem(@PathVariable("itemCode") String itemCode, Model model){
  	   Ims item = null;
  	   try{
  		   imsServiceMVC.deleteItemByItemCode(itemCode);
  	   }
  	   catch(Exception e){
  		   e.printStackTrace();
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
     public String deleteItem(@ModelAttribute("item") Ims item, Model model){
  	   try{
  		   imsServiceMVC.deleteItem(item);
  	   }
  	   catch(Exception e){
  		   e.printStackTrace();
  	   }
  	   model.addAttribute("itemCode", item.getItemcode());
  	   model.addAttribute("operation", "Deleted");
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
     
     //------------------- ModelAttributes Initiation ------------------//
     
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
    
}
