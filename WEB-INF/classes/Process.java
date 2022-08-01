import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.*;

@MultipartConfig
public class Process extends HttpServlet{
	PrintWriter user_profile;
	PrintWriter res_out;
	BufferedReader template_in;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{

		//ACQUIRE THE DATA FROM CLIENT
		String title=request.getParameter("title");
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String education=request.getParameter("education");
		String profession=request.getParameter("profession");
		String skills=request.getParameter("skills");
		String template=request.getParameter("template");
		String more_info=request.getParameter("more-info");
		String mobile=request.getParameter("mobile");
		String mail=request.getParameter("mail");
		String facebook=request.getParameter("facebook");
		String instagram=request.getParameter("instagram");
		String linkedin=request.getParameter("linkedin");
		String twitter=request.getParameter("twitter");
		String youtube=request.getParameter("youtube");
		String action=request.getParameter("action");
		String image_name=request.getParameter("image");

		System.out.println("Data Read");
		//GETTING THE OUPUT WRITER OBJECT
		res_out=response.getWriter();
		response.setContentType("text/html");

		//CHECK ACTION
		if(action.equals("host")){
			user_profile=new PrintWriter("C:\\xampp\\tomcat\\webapps\\ProfileMakers\\host\\"+mobile+"-"+name+".html");
			System.out.println("Hosted");
		}
		else{
			user_profile=new PrintWriter("C:\\xampp\\tomcat\\webapps\\ProfileMakers\\download\\"+mobile+"-"+name+".html");
			System.out.println("Downloaded");
		}

		//INSERT TEMPLATE STRUCTURE IN THE USER PROFILE
		template_in=new BufferedReader(new FileReader("C:\\xampp\\tomcat\\webapps\\ProfileMakers\\WEB-INF\\classes\\"+template+".html"));
		while(true){
			String line=template_in.readLine();
			if(line==null)
				break;
			user_profile.println(line);
			System.out.println("Template Copied");
		}

		//INSERT DATA INPUT FROM THE USER REQUEST INTO THEIR PROFILE
		user_profile.println("<script>");
		user_profile.println("document.getElementById('title').innerHTML=`"+title+"`;");
		user_profile.println("document.getElementById('name').innerHTML=`"+name+"`;");
		user_profile.println("document.getElementById('address').innerHTML=`"+address+"`;");
		System.out.println("Title Name Address Taken");
		if(education!=null)
			user_profile.println("document.getElementById('education').innerHTML=`"+education+"`;");
		else
			user_profile.println("document.getElementById('education').innerHTML='-';");

		user_profile.println("document.getElementById('profession').innerHTML=`"+profession+"`;");

		if(skills!=null)
			user_profile.println("document.getElementById('skills').innerHTML=`"+skills+"`;");
		else
			user_profile.println("document.getElementById('skills').innerHTML='-';");

		if(more_info!=null)
			user_profile.println("document.getElementById('more-info').innerHTML=`"+more_info+"`;");
		else
			user_profile.println("document.getElementById('more-info').innerHTML='-';");

		user_profile.println("document.getElementById('mobile').innerHTML=`"+mobile+"`;");
		user_profile.println("document.getElementById('mail').href=`mailto:"+mail+"`;");

		if(facebook!=null)
			user_profile.println("document.getElementById('facebook').href=`"+facebook+"`;");
		else
			user_profile.println("document.getElementById('facebook').hidden=true;");

		if(twitter!=null)
			user_profile.println("document.getElementById('twitter').href=`"+twitter+"`;");
		else
			user_profile.println("document.getElementById('twitter').hidden=true;");

		if(instagram!=null)
			user_profile.println("document.getElementById('instagram').href=`"+instagram+"`;");
		else
			user_profile.println("document.getElementById('instagram').hidden=true;");

		if(linkedin!=null)
			user_profile.println("document.getElementById('linkedin').href=`"+linkedin+"`;");
		else
			user_profile.println("document.getElementById('linkedin').hidden=true;");

		if(youtube!=null)
			user_profile.println("document.getElementById('youtube').href=`"+youtube+"`;");
		else
			user_profile.println("document.getElementById('youtube').hidden=true;");

		if(action.equals("host")){
			Part part = request.getPart("image-file");
			part.write("C:\\xampp\\tomcat\\webapps\\ProfileMakers\\host\\"+mobile+"-"+name+".png");
			user_profile.println("document.getElementById('image').src=`http://localhost:8080/ProfileMakers/host/"+mobile+"-"+name+".png`;");
			System.out.println("Image copied");
		}
		else{
			user_profile.println("document.getElementById('image').src='/"+image_name+"';");
		}
		user_profile.println("</script>");	//CLOSING HTML TAGS WHICH WERE NOT CLOSED IN TEMPLATE FILES
		user_profile.println("</html>");
		System.out.println("Tags Closed");
		user_profile.close();
		template_in.close();

		if(action.equals("host")){
			BufferedReader cin=new BufferedReader(new FileReader("C:\\xampp\\tomcat\\webapps\\ProfileMakers\\WEB-INF\\classes\\copy.html"));
			while(true){
				String line=cin.readLine();
				if(line==null)
					break;
				res_out.println(line);
			}
			res_out.println("<script>function copy(){navigator.clipboard.writeText('http://localhost:8080/ProfileMakers/host/"+mobile+"-"+name+".html');alert('Link Copied to Clipboard');}</script></html>");
			cin.close();
		}
		else{
			BufferedReader din=new BufferedReader(new FileReader("C:\\xampp\\tomcat\\webapps\\ProfileMakers\\WEB-INF\\classes\\download.html"));
			while(true){
				String line=din.readLine();
				if(line==null)
					break;
				res_out.println(line);
			}
			res_out.println("<script>document.getElementById('download').href='http://localhost:8080/ProfileMakers/download/"+mobile+"-"+name+".html'</script></html>");
			din.close();
		}


		res_out.close();
		System.out.println("Served Completely");
	}
}