import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SorteioServlet")
public class SorteioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Random random = new Random();
        
        //Pegar os resultados dos inputs
        int num1 = Integer.parseInt(request.getParameter("num1"));
        int num2 = Integer.parseInt(request.getParameter("num2"));
        int qtd = Integer.parseInt(request.getParameter("qtd"));
         
        
        
        /*
        Verifica se a quantidade de numeros que o usuário quer que seja sorteado seja maior que a quantidade
        de numeros informado, caso seja maior ele não entra no if e mostra uma pagina de erro
        */
        if (Math.abs(num1 - num2) > qtd) {
            int min = Math.min(num1, num2);
            int max = Math.max(num1, num2);

            
          Set<Integer> numerosSorteados = new HashSet<>();

            //Faz o sorteio
            while (numerosSorteados.size() < qtd) {
                int numeroAleatorio = random.nextInt(max - min + 1) + min;
                numerosSorteados.add(numeroAleatorio);  // Adiciona ao conjunto (não permite duplicatas)
            }

            // Tirando a variavel de set para string , para tirar os colchetes
            String numerosSorteadosStr = numerosSorteados.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .trim();

            //Respostas dos numero sorteados
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            out.println("<html><head>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; }");
            out.println("body {background-color:black; text-align: center; }");
            out.println(".h1Titulo { text-shadow:  -2px 1px 15px rgba(80, 255, 119, 0.54),  2px -1px 15px rgba(80, 255, 119, 0.54); color:white }");
            out.println(".resultado{text-shadow:  -1px -1px 38px rgba(11,236,160,1); color:#50ff77; font-size:30px}");
            out.println(".inputInicio{color:#50ff77;background-color: black; border-color: white; border-style: solid; border-width: 1px; height: 40px; width: 150px; font-size: 20px; font-family: bradley hand, cursive;   border-radius: 5px;  text-shadow:-2px 1px 15px rgba(80, 255, 119, 0.54), 2px -1px 15px rgba(80, 255, 119, 0.54);}");
            out.println(".inputSortearNovamente{color:#50ff77;background-color: black; border-color: white; border-style: solid; border-width: 1px; height: 40px; width: 250px; font-size: 20px; font-family: bradley hand, cursive;   border-radius: 5px;  text-shadow:-2px 1px 15px rgba(80, 255, 119, 0.54), 2px -1px 15px rgba(80, 255, 119, 0.54);}");
            out.println("p { font-size: 18px; }");
            out.println("a { text-decoration: none; color: green; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<h1 class=\"h1Titulo\" >Resultado do Sorteio</h1>");
            out.println("<p class=\"resultado\">" + numerosSorteadosStr + "</p>");
            out.println("<a href='index.html'><button class=\"inputInicio\">Inicio</button></a>");
            out.println("<button class=\"inputSortearNovamente\" onclick=\"location.reload();\">Sortear novamente</button>");
            out.println("</body></html>");
        } else {
            //Pagina de erro
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><head>");
            out.println("<style>");
            out.println("body {background-color:black; font-family: Arial, sans-serif; text-align: center;}");
            out.println("p{color:white;}");
            out.println("h1 { color: red; }");
            out.println(".inputInicio{color:red;background-color: black; border-color: white; border-style: solid; border-width: 1px; height: 40px; width: 150px; font-size: 20px; font-family: bradley hand, cursive;   border-radius: 5px;  text-shadow:-2px 1px 15px rgba(80, 255, 119, 0.54), 2px -1px 15px rgba(80, 255, 119, 0.54);}");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<h1>Verifique suas informações</h1>");
            out.println("<p>Você escolheu mais números para serem sorteados do que a quantidade de números que você tem.</p>");
            out.println("<a href='index.html'><button class=\"inputInicio\">Inicio</button></a>");
            out.println("</body></html>");
            
        }
    }
}