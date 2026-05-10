package testproject;

import com.ibm.wala.core.util.config.AnalysisScopeReader;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.ipa.cha.ClassHierarchyFactory;
import com.ibm.wala.ipa.cha.IClassHierarchy;

import java.io.File;

public class WalaTest {
    public static void main(String[] args) throws Exception {
        System.out.println("Loading jar: " + args[0]);
        AnalysisScope scope = AnalysisScopeReader.instance.makeJavaBinaryAnalysisScope(args[0], null);
        IClassHierarchy cha = ClassHierarchyFactory.make(scope);
        System.out.println("CHA size: " + cha.getNumberOfClasses());
        
        System.out.println("Ltestproject/MainEntry exists: " + (cha.lookupClass(com.ibm.wala.types.TypeReference.findOrCreate(com.ibm.wala.types.ClassLoaderReference.Application, "Ltestproject/MainEntry")) != null));
    }
}
