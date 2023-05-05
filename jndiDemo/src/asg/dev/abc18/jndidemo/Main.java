package asg.dev.abc18.jndidemo;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Hashtable<String, Object> env = new Hashtable<String, Object>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:1389/ou=People,dc=example,dc=com");
//		env.put(Context.PROVIDER_URL, "ldaps://172.22.69.237:1636");
		env.put(Context.SECURITY_PRINCIPAL, "cn=Directory Manager");
		env.put(Context.SECURITY_CREDENTIALS, "password");
//		env.put(Context.SECURITY_PROTOCOL, "ssl");

		try {
//			Context ctx = new InitialContext(env);
        DirContext ctx = new InitialDirContext(env);

//			Attributes attrs = ctx.getAttributes("ldap://localhost:1389", new String[] {"supportedSASLMechanisms"});
//			printAttrs(attrs);
//			LdapContext a01 = (LdapContext) ctx.lookup("cn=abc01,ou=People");
//			System.out.println(a01);
//
//			NamingEnumeration list01 = ctx.list("ou=People");
//
//			while (list01.hasMore()) {
//				System.out.println((NameClassPair) list01.next());
//			}
//
//			NamingEnumeration binding01 = ctx.listBindings("");
//
//			while (binding01.hasMore()) {
//				Binding bd01 = (Binding) binding01.next();
//				System.out.println(bd01.getName());
////				LdapContext ctx01 = (LdapContext) bd01.getObject();
////				String[] aids = {"cn","objectclass"};
//
////				Attributes attrs = ctx.getAttributes(bd01.getName(), aids);
//				Attributes attrs = ctx.getAttributes(bd01.getName());
//
//				NamingEnumeration list02 = attrs.getAll();
//				while (list02.hasMore()) {
//					Attribute attr = (Attribute) list02.next();
//					System.out.println("Attribute : " + attr.getID());
//
//					NamingEnumeration list03 = attr.getAll();
//					while (list03.hasMore()) {
//						System.out.println("Value: " + list03.next());
//					}
//				}
//				System.out.println("*".repeat(20));
//
//			}

//			Attributes attrs = new BasicAttributes(true);
//			Attribute attr = new BasicAttribute("objectclass");
//			attr.add("top");
//			attr.add("oragnizationalUnit");
//			attrs.put(attr);
//
//			Context ctx02 = ctx.createSubcontext("ou=ASG", attrs);
//
//			NamingEnumeration binding02 = ctx02.listBindings("");
//
//			while(binding02.hasMore()) {
//				Binding bd01 = (Binding) binding01.next();
//				LdapContext ctx01 = (LdapContext)bd01.getObject();
//
//				System.out.println(ctx01);
//
//			}

//			Attributes matchAttrs = new BasicAttributes(true);
//			matchAttrs.put(new BasicAttribute("objectclass","groupOfNames"));
//			matchAttrs.put(new BasicAttribute("member"));

        String[] aids = {"cn","member"};
//
////			NamingEnumeration list01 = ctx.search("",matchAttrs);
//			NamingEnumeration list01 = ctx.search("",matchAttrs, aids);

        SearchControls ctrs = new SearchControls();

        String attrFilter01 = "(&(objectclass=groupOfNames)(member=*))";
//			NamingEnumeration list01 = ctx.search("", attrFilter01,ctrs);

        ctrs.setReturningAttributes(aids);
//			ctrs.setSearchScope(SearchControls.SUBTREE_SCOPE);
//			ctrs.setSearchScope(SearchControls.ONELEVEL_SCOPE);
//			ctrs.setSearchScope(SearchControls.OBJECT_SCOPE);
//			ctrs.setCountLimit(10);
//			ctrs.setTimeLimit(1000);

        NamingEnumeration list01 = ctx.search("", attrFilter01,ctrs);
//
//
        while(list01.hasMore()) {
            SearchResult sr = (SearchResult) list01.next();
//				System.out.println(">> " + sr.getNameInNamespace());
            System.out.println(">> " + sr.getName());
//				printAttrs(ctx.getAttributes(sr.getName()));
            printAttrs(sr.getAttributes());

        }


        ctx.close();

        List<Integer> z01 = new ArrayList<Integer>();
        z01.add(1);
        z01.add(2);
        List<String> z02 = new ArrayList<String>();
        z02.add("abc01");
        z02.add("abc02");

        System.out.println(z01.getClass().getSimpleName());
        System.out.println(z02.getClass().getSimpleName());


    } catch (NamingException e) {
        System.out.println(e);
    }

}

    static void printAttrs(Attributes attrs) {
        if (attrs == null) {
            System.out.println("No attributes");
        } else {
            /* Print each attribute */
            try {
                for (NamingEnumeration ae = attrs.getAll();
                     ae.hasMore();) {
                    Attribute attr = (Attribute)ae.next();
                    System.out.println("attribute: " + attr.getID());

                    /* print each value */
                    for (NamingEnumeration e = attr.getAll();
                         e.hasMore();
                         System.out.println("value: " + e.next()))
                        ;
                }
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }
}