package com.bedrosians.bedlogic.dao.item;


import java.util.Set;

import org.hibernate.Session;

import com.bedrosians.bedlogic.dao.GenericDao;
import com.bedrosians.bedlogic.domain.item.ColorHue;


public interface ColorHueDao extends GenericDao<ColorHue, String>{
  
   public String createColorHue(Session session, ColorHue colorHue);
   public void createColorHues(Set<ColorHue> colorHues);
   public void updateColorHue(ColorHue colorHue);
   public void deleteColorHue(ColorHue colorHue);	

}