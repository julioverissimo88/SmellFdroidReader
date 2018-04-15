package com.company;

import org.eclipse.jgit.api.Git;

public class GitRepository {
    public static void CloneRepostory(String repository){
        try{
            Git git = Git.cloneRepository()
                    .setURI( repository )
                    .call();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }


    }

}
