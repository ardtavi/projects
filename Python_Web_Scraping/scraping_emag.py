import os
import urllib.request   
from bs4 import BeautifulSoup as soup 

search_var="placi video"
page_url = "https://www.emag.ro/placi_video/c?ref=search_menu_category"
base_url = "https://www.emag.ro"
search_url = "https://www.emag.ro/search/"+search_var+"?ref=effective_search"
# opens the connection and downloads html page from url
out_filename = "Produse_emag.csv"
# header of csv file to be written
headers = "Nume_produs,Pret,Furnizor,Link_imagine,Link_produs,id_shop\n"
# changes to directory where files will be saved 
dirname = os.path.dirname(__file__)
filename= os.path.join(dirname, 'csvs')
os.chdir(filename)
#os.chdir("E:\Licenta\Web Scraping\csvs")
# opens the file and writes headers
f = open(out_filename, "w", encoding="utf-8-sig")
f.write(headers)

class AppURLopener(urllib.request.FancyURLopener):
    version = "Mozilla/5.0"

def parse_page(page_url,count):
    
    opener = AppURLopener()
    uClient = opener.open(page_url)

    # parses html into a soup data structure to traverse html
    page_soup = soup(uClient.read(), "html.parser")
    uClient.close()


    # We save all the products 
    containers = page_soup.findAll("div", {"class": "card-item js-product-data"})
  
    #titlecontainer= container.findAll("div", {"class":"data - name"})
    #to=titlecontainer[0].text
    #a_to=to.split()
    #product_name=" ".join(a_to)

    # loops over each product and scrapes product features
   
    for container in containers:
        count=count+1
        #pricecontainer=container.findAll("p", {"class":"product-new-price"})
        
        pricecontainer=container.find("p", {"class":"product-new-price"})
        sup_tag= pricecontainer.sup
        span_tag= pricecontainer.span
        sup_tag.decompose()
        span_tag.decompose()
        price=pricecontainer.text
        price=  price.replace(".", "")

        titlecontainer=container.findAll("a", {"class":"product-title js-product-url"})
        to=titlecontainer[0].text
        a_to=to.split()
        product_name=" ".join(a_to)
               
        furnizor= "eMag.ro"

        imagelinkcontainer=container.find("img", {"class":"lozad"}).get("data-src")

        linkcontainer=container.find("a", {"class":"thumbnail-wrapper js-product-url"}).get("href")
        
        

        # prints the dataset to console
        print("product_name: " + product_name + "\n")
        print("price: " + price + "\n")
        print("Image link: " + imagelinkcontainer + "\n")
        print("Product link: " + linkcontainer + "\n")
        
        for ch in ["â","„","Â", "â",",","™","®", "Placa video "]:
            if ch in product_name:
                product_name= product_name.replace(ch, "")
                
        for ch in ["â","„","Â", "â",",","™"," ", "Lei"]:
            if ch in price:
                price= price.replace(ch, "")


        # writes the dataset to file
        
        f.write(product_name+ ", " + price + ", "  + furnizor+ ", "+ imagelinkcontainer+", "+ linkcontainer+", "+ str(count)+"\n")
    
    #check if last last page or not
    Next_button=page_soup.findAll("a", {"class": "js-change-page"})[-1].text
    if Next_button == "Pagina urmatoare":
        partial_url=page_soup.findAll("a", {"class": "js-change-page"})[-1].get("href")
        page_url=base_url+partial_url
        parse_page(page_url, count)    
        #print(Next_button)
        #print(partial_url)
    
parse_page(page_url,0)
print("Import from website: "+ base_url + " finished with success")   
f.close()  # Close the file
