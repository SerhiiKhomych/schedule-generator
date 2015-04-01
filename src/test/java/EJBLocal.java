import ejb.DatabaseEntities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import javax.inject.Singleton;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by Sergey on 28.12.2014.
 */
@Singleton
public class EJBLocal {
    private static DatabaseEntities entities;

    public EJBLocal() {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
        props.put(Context.PROVIDER_URL, "127.0.0.1:4221");
        Context context = null;
        try {
            context = new InitialContext(props);
            entities = (DatabaseEntities) context.lookup("DatabaseEntitiesLocalBean");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public DatabaseEntities getEntities() {
        return entities;
    }

    public void setEntities(DatabaseEntities entities) {
        this.entities = entities;
    }
}
