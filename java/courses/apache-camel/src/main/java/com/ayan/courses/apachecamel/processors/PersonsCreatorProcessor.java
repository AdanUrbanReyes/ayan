package com.ayan.courses.apachecamel.processors;

import com.ayan.courses.apachecamel.enums.SexEnum;
import com.ayan.courses.apachecamel.models.Person;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PersonsCreatorProcessor implements Processor {

    private static long personId = 0;
    private static final Random RANDOM = new Random();
    private static final byte MIN_AGE = 0;
    private static final byte MAX_AGE = 100;
    private static final byte MAX_FULL_NAME_LENGTH = 30;
    private static final String[] DUMMY_FULL_NAMES = new String[]{
            "Paddy O’Furniture",
            "Olive Yew",
            "Aida Bugg",
            "Maureen Biologist",
            "Teri Dactyl",
            "Peg Legge",
            "Allie Grater",
            "Liz Erd",
            "A. Mused",
            "Constance Noring",
            "Lois Di Nominator",
            "Minnie Van Ryder",
            "Lynn O’Leeum",
            "P. Ann O’Recital",
            "Ray O’Sun",
            "Lee A. Sun",
            "Ray Sin",
            "Isabelle Ringing",
            "Eileen Sideways",
            "Rita Book",
            "Paige Turner",
            "Rhoda Report",
            "Augusta Wind",
            "Chris Anthemum",
            "Anne Teak",
            "U.R. Nice",
            "Anita Bath",
            "Harriet Upp",
            "I.M. Tired",
            "I. Missy Ewe",
            "Ivana B. Withew",
            "Anita Letterback",
            "Hope Furaletter",
            "B. Homesoon",
            "Bea Mine",
            "Bess Twishes",
            "C. Yasoon",
            "Audie Yose",
            "Dee End",
            "Amanda Hug",
            "Ben Dover",
            "Eileen Dover",
            "Willie Makit",
            "Willie Findit",
            "Skye Blue",
            "Staum Clowd",
            "Addie Minstra",
            "Anne Ortha",
            "Dave Allippa",
            "Dee Zynah",
            "Hugh Mannerizorsa",
            "Loco Lyzayta",
            "Manny Jah",
            "Mark Ateer",
            "Reeve Ewer",
            "Tex Ryta",
            "Theresa Green",
            "Barry Kade",
            "Stan Dupp",
            "Neil Down",
            "Con Trariweis",
            "Don Messwidme",
            "Al Annon",
            "Anna Domino",
            "Clyde Stale",
            "Anna Logwatch",
            "Anna Littlical",
            "Norma Leigh Absent",
            "Sly Meebuggah",
            "Saul Goodmate",
            "Faye Clether",
            "Sarah Moanees",
            "Ty Ayelloribbin",
            "Hugo First",
            "Percy Vere",
            "Jack Aranda",
            "Olive Tree",
            "Fran G. Pani",
            "John Quil",
            "Ev R. Lasting",
            "Anne Thurium",
            "Cherry Blossom",
            "Glad I. Oli",
            "Ginger Plant",
            "Del Phineum",
            "Rose Bush",
            "Perry Scope",
            "Frank N. Stein",
            "Roy L. Commishun",
            "Pat Thettick",
            "Percy Kewshun",
            "Rod Knee",
            "Hank R. Cheef",
            "Bridget Theriveaquai",
            "Pat N. Toffis",
            "Karen Onnabit",
            "Col Fays",
            "Fay Daway",
            "Joe V. Awl",
            "Wes Yabinlatelee",
            "Colin Sik",
            "Greg Arias",
            "Toi Story",
            "Gene Eva Convenshun",
            "Jen Tile",
            "Simon Sais",
            "Peter Owt",
            "Hugh N. Cry",
            "Lee Nonmi",
            "Lynne Gwafranca",
            "Art Decco",
            "Lynne Gwistic",
            "Polly Ester Undawair",
            "Oscar Nommanee",
            "Laura Biding",
            "Laura Norda",
            "Des Ignayshun",
            "Mike Rowe-Soft",
            "Anne T. Kwayted",
            "Wayde N. Thabalanz",
            "Dee Mandingboss",
            "Sly Meedentalfloss",
            "Stanley Knife",
            "Wynn Dozeaplikayshun",
            "Mal Ajusted",
            "Penny Black",
            "Mal Nurrisht",
            "Polly Pipe",
            "Polly Wannakrakouer",
            "Con Staninterupshuns",
            "Fran Tick",
            "Santi Argo",
            "Carmen Goh",
            "Carmen Sayid",
            "Norma Stitts",
            "Ester La Vista",
            "Manuel Labor",
            "Ivan Itchinos",
            "Ivan Notheridiya",
            "Mustafa Leek",
            "Emma Grate",
            "Annie Versaree",
            "Tim Midsaylesman",
            "Mary Krismass",
            "Tim “Buck” Too",
            "Lana Lynne Creem",
            "Wiley Waites",
            "Ty R. Leeva",
            "Ed U. Cayshun",
            "Anne T. Dote",
            "Claude Strophobia",
            "Anne Gloindian",
            "Dulcie Veeta",
            "Abby Normal"
    };

    @Override
    public void process(Exchange exchange) throws Exception {
        byte ra = (byte) (RANDOM.nextInt(MAX_AGE - MIN_AGE) + MIN_AGE);
        int ros = RANDOM.nextInt(SexEnum.values().length);
        int rif = RANDOM.nextInt(DUMMY_FULL_NAMES.length);
        exchange.getMessage().setBody(new Person(DUMMY_FULL_NAMES[rif], ra, SexEnum.values()[ros]));
        exchange.getMessage().setHeader("id", personId++);
    }

}
