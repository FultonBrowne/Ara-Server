package com.andromeda.araserver.test
import com.andromeda.araserver.skills.*
import com.andromeda.araserver.util.ParseUrl
import java.util.*
import org.junit.Test
class SkillsTest{
   @Test
   fun call(){
      val params = ParseUrl.ApiParams("call bob", "0.0", "0.0", Locale.US, "bvbjrblvrvrkvblfkvn")
   }
   @Test
   fun text(){
      val params = ParseUrl.ApiParams("text bob", "0.0", "0.0", Locale.US, "bvbjrblvrvrkvblfkvn")

   }
   @Test
   fun timer(){
      val params = ParseUrl.ApiParams("set a timer for 5 minutes", "0.0", "0.0", Locale.US, "bvbjrblvrvrkvblfkvn")

   }
}
