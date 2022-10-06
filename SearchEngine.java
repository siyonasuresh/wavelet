import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    List<String> params = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/add")) {
            String [] parameters = url.getQuery().split("=");
           if(parameters[1].equals("anewstringtoadd")){
               return "Add String to Query";
           }
           else{
                params.add(parameters[1]);
                return "String is added";
           }
        }
        else if(url.getPath().equals("/search")){
            String [] parameters = url.getQuery().split("=");
            String toreturn = "This search produced the words: ";
            for (String word: params){
                if(word.contains(parameters[1])){
                    toreturn = toreturn + word + " ";
                } 
            }
            return toreturn;
        }
        return "Error!!!";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
