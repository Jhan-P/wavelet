import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> list = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Jhan and Will's number: %d", num);
        } else if (url.getPath().equals("/increment")) {
            num += 1;
            return String.format("Number incremented!");
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    String query = parameters[1];
                    list.add(query);
                    return String.format("%s added to the list", query);
                }
            }
            else if (url.getPath().contains("/search")) {
            String result = "";
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                for(String word: list){
                    if(word.contains(parameters[1])){
                        result += word;
                    }
                }
                return result;
            }
            
        }
        }
        
            return "404 Not Found!";
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
