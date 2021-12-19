package ar.unrn.service;

import org.ektorp.CouchDbInstance;
import org.ektorp.support.CouchDbRepositorySupport;

import ar.unrn.model.Page;

public class PageRepository extends CouchDbRepositorySupport<Page> {

    public PageRepository(CouchDbInstance dbInstance) {
        super(Page.class, dbInstance.createConnector("page", true));
    }

}