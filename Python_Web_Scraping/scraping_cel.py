import os
from urllib.request import urlopen as req  # Web client
from urllib import parse
from bs4 import BeautifulSoup as soup  # HTML data structure

page_url = "https://www.cel.ro/placi-video/"
base_url = "https://www.cel.ro"
# opens the connection and downloads html page from url
out_filename = "Produse_cel.csv"
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
global id_shop
id_shop = 0


def parse_page(page_url,count):
    
    uClient = req(page_url)

    # parses html into a soup data structure to traverse html
    # as if it were a json data type.
    page_soup = soup(uClient.read(), "html.parser")
    uClient.close()


    # finds each product from the store page
    containers = page_soup.findAll("div", {"class": "product_data productListing-tot"})
    
    # name the output file to write to local disk
  

    #titlecontainer= container.findAll("div", {"class":"data - name"})
    #to=titlecontainer[0].text
    #a_to=to.split()
    #product_name=" ".join(a_to)

    # loops over each product and scrapes product features
  
  
    for container in containers:
        count=count+1
        pricecontainer=container.find("div", {"class":"pret_n"})
        pricecontainer1=pricecontainer.find("b", {"class": None})
        
       
    
    
        price=pricecontainer1.text
        

        titlecontainer=container.findAll("a", {"class":"productListing-data-b product_link product_name"})
        product_name=titlecontainer[0].text
        product_name=product_name.replace("\n"," ")

        for ch in ["â","„","Â", "â",",","™","®", "Placa video "]:
            if ch in product_name:
                product_name= product_name.replace(ch, "")
        product_name= product_name.replace(" ", "",1)


        furnizor= "cel.ro"
        imagelinkcontainer=container.find("img", {"class": None }).get("data-src")
        linkcontainer=container.find("a", {"productListing-data-b product_link product_name"}).get("href")
        link=parse.urljoin(base_url, linkcontainer)

        # prints the dataset to console
        print("product_name: " + product_name + "\n")
        print("price: " + price + "\n")
        print("Image link: " + imagelinkcontainer + "\n")
        print("Product link: " + link + "\n")


        # writes the dataset to file
        f.write(product_name.replace("Placa video  ", "") + ", " + price + ", "  + furnizor+  ", "+ imagelinkcontainer+", "+ link+ ", "+ str(count)+ "\n")

    
    #check if last last page or not
    try:
        Next_button=page_soup.findAll("a", {"class": "last"})[-1].text
    except IndexError:
        Next_button = 'null'
       
    if Next_button == ">>":
        partial_url=page_soup.findAll("a", {"class": "last"})[-1].get("href")
        #print(Next_button)
        #print(partial_url)
        page_url=parse.urljoin(base_url, partial_url)
        parse_page(page_url,count)
    
         
    
parse_page(page_url,0)
print("Import from website: "+ base_url + " finished with success")   
f.close()  # Close the file
