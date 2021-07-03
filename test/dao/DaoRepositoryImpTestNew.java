package dao;

import daolar.DaoRepositoryImp;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DaoRepositoryImpTestNew {
    @Mock
    EntityManager entityManager;
    @Mock
    EntityTransaction transaction;
    //Field t of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    DaoRepositoryImp daoRepositoryImp;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }




}

