package Groupld.Server.Util;

import org.hibernate.cfg.Configuration;

public class HibernateManager {
    public HibernateManager(){
        Configuration cfg = new Configuration();
                .addClass(org.hibernate.auction.Item.class)
                .addClass(org.hibernate.auction.Bid.class);
    }
}
