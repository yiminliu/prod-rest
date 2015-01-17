package com.bedrosians.bedlogic.dao.ims;


import java.util.Set;

import org.hibernate.Session;

import com.bedrosians.bedlogic.dao.GenericDao;
import com.bedrosians.bedlogic.domain.ims.ColorHue;


public interface ColorHueDao extends GenericDao<ColorHue, String>{
  
   public Set<ColorHue> getAllColorHues(); 
   public Set<ColorHue> getItemColorHues(String itemCode); 
   public String createColorHue(Session session, ColorHue colorHue);
   public void createColorHues(Set<ColorHue> colorHues);
   public void updateColorHue(ColorHue colorHue);
   public void deleteColorHue(ColorHue colorHue, boolean newSession);	

}