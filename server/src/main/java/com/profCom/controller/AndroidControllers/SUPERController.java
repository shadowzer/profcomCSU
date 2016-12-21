package com.profCom.controller.AndroidControllers;

import com.profCom.entity.Category;
import com.profCom.entity.News;
import com.profCom.entity.NewsCategory;
import com.profCom.service.News.NewsService;
import com.profCom.service.NewsCategory.NewsCategoryService;
import com.profCom.service.User.UserService;
import com.profCom.service.UserCategory.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VolgiNN on 20.12.2016.
 */
@RestController
public class SUPERController {
    @Autowired
    UserService us;
    @Autowired
    UserCategoryService ucs;
    @Autowired
    NewsService ns;
    @Autowired
    NewsCategoryService ncs;

    @RequestMapping(value = "/User/News/{Uid}", method = RequestMethod.GET)
    @ResponseBody
    public List<News> getUserCategories(@PathVariable("Uid") Long Uid){
        List<News> listN=new ArrayList<News>();
        News tmp;
        List<Category> listC=ucs.getUserCategories(Uid);
        for (Category cat:listC) {
            List<NewsCategory> listNC = ncs.findByCategory(cat);
            for (NewsCategory nc:listNC){
                tmp=ns.getByID(nc.getNews().getId());
                if ((tmp!=null)&&(!containNewsInList(tmp, listN))){
                    listN.add(tmp);
                }
            }
        }
        return listN;
    }
    private boolean containNewsInList(News N,List<News> listN){
        boolean contain=false;
        for (News ntmp:listN)
        {
            if (N.getId()==ntmp.getId()) contain=true;
        }
        return contain;
    }
}
