import os
from urllib.request import urlopen as req  
from bs4 import BeautifulSoup as soup 

search_var="placi video"
page_url = "https://altex.ro/placi-video-calculator/cpl/"
base_url = "https://www.altex.ro"
search_url = "https://altex.ro/cauta/?q="+search_var
# opens the connection and downloads html page from url
out_filename = "Produse_altex.csv"
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
def parse_page(page_url,count):
    
    uClient = req(page_url)

    # parses html into a soup data structure to traverse html
    page_soup = soup(uClient.read(), "html.parser")
    uClient.close()


    # We save all the products 
    containers = page_soup.findAll("div", {"class": "Product"})
  
    # loops over each product and scrapes product features
 
    for container in containers:
        count=count+1
        pricecontainer=container.find("span", {"class":"Price-int"})
        price=pricecontainer.text
        price=  price.replace(".", "")

        titlecontainer=container.findAll("a", {"class":"Product-name"})
        to=titlecontainer[0].text
        a_to=to.split()
        product_name=" ".join(a_to)
               
        furnizor= "altex.ro"

        imagelinkcontainer=container.find("img", {"class":"Product-photo"}).get("src")

        linkcontainer=container.find("a", {"class":"Product-photoTrigger js-ProductClickListener"}).get("href")
        
        

        # prints the dataset to console
        print("product_name: " + product_name + "\n")
        print("price: " + price + "\n")
        print("Image link: " + imagelinkcontainer + "\n")
        print("Product link: " + linkcontainer + "\n")
        
        for ch in ["â","„","Â", "â",",","™","®", "Placa video "]:
            if ch in product_name:
                product_name= product_name.replace(ch, "")


        # writes the dataset to file
        
        f.write(product_name+ ", " + price + ", "  + furnizor+ ", "+ imagelinkcontainer+", "+ linkcontainer+", "+ str(count)+"\n")
    
    #check if last last page or not
    Next_button=page_soup.findAll("a", {"class": "Button Button--forSelect js-trigger-catalog-apply-filters"})[-1].get("title")
    if Next_button=="Inainte":
        partial_url=page_soup.findAll("a", {"class": "Button Button--forSelect js-trigger-catalog-apply-filters"})[-1].get("href")
        page_url=partial_url
        parse_page(page_url,count) 
      
        
    
parse_page(page_url,0)
print("Import from website: "+ base_url + " finished with success")   
f.close()  # Close the file
