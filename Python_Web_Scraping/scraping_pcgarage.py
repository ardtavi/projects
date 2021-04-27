
import os
import urllib.request  # Web client
from bs4 import BeautifulSoup as soup  # HTML data structure

page_url = "https://www.pcgarage.ro/placi-video/"
base_url = "https://www.pcgarage.ro"
# opens the connection and downloads html page from url
out_filename = "Produse_PCGarage.csv"
# header of csv file to be written
headers = "Nume_produs,Pret,Furnizor,Link_imagine,Link_produs,id_shop\n"
#os.chdir("E:\Licenta\Web Scraping\csvs")
dirname = os.path.dirname(__file__)
filename= os.path.join(dirname, 'csvs')
os.chdir(filename)
# opens file, and writes headers
f = open(out_filename, "w", encoding="utf-8-sig")
f.write(headers)


class AppURLopener(urllib.request.FancyURLopener):
    version = "Mozilla/5.0"



def parse_page(page_url,count):
    
    opener = AppURLopener()
    uClient = opener.open(page_url)
    #uClient = uReq(page_url)
    

    # parses html into a soup data structure to traverse html
    # as if it were a json data type.
    page_soup = soup(uClient.read(), "html.parser")
    uClient.close()


    # finds each product from the store page
    containers = page_soup.findAll("div", {"class": "product_box_parent col-xs-24 col-sm-12 col-md-8 col-lg-6 col-xl-4 col-xxl-3"})
    print(len(containers))
    # name the output file to write to local disk
  

    #titlecontainer= container.findAll("div", {"class":"data - name"})
    #to=titlecontainer[0].text
    #a_to=to.split()
    #product_name=" ".join(a_to)

    # loops over each product and grabs attributes about
    # each product
 
    for container in containers:
        count=count+1
        


        #pricecontainer=container.findAll("p", {"class":"product-new-price"})
        
        pricecontainer=container.findAll("p", {"class":"price"})
       
        
        try:
            price=pricecontainer[0].text
         
        except IndexError: 
            print("No price found")
            continue    
             
        
        split_string = price. split(",", 1) 
        price = split_string[0]
       

        price=  price.replace(".", "")
       


        titlecontainer=container.findAll("div", {"class":"product_box_name"})

        try:
            imagelinkcontainer=container.findAll("source")[-1].get("srcset")
        except IndexError:
            imagelinkcontainer = 'null'


        

        #to=titlecontainer[0].text
        #a_to=to.split()
        #product_name=" ".join(a_to)
        product_name=titlecontainer[0].text
       
        
        furnizor= "PCGarage.ro"
        try:
            #tempcontainer= container.find("div", {"class":"product_box_image img_left"}).a.get("href")
            tempcontainer= container.find("div", {"class":"product_box_image img_left"})
            if tempcontainer is not None:
                linkcontainer= tempcontainer.a.get("href")
        except IndexError:
            tempcontainer = 'null'
        
        try:
            
            tempcontainer= container.find("div", {"product_box_image img_center"})
            if tempcontainer is not None:
                linkcontainer= tempcontainer.a.get("href")
        except IndexError:
            tempcontainer = 'null'       

        # prints the dataset to console
        print("product_name: " + product_name + "\n")
        print("price: " + price + "\n")
        print("Image link: " + imagelinkcontainer + "\n")
       
        print("Product link: " + linkcontainer + "\n")

        for ch in ["â","„","Â", "â","  "]:
            if ch in product_name:
                product_name= product_name.replace(ch, "")


        # writes the dataset to file
        f.write(product_name.replace(",", "") + ", " + price + ", "  + furnizor+ ", "+ imagelinkcontainer+", "+ linkcontainer+", "+ str(count) +"\n")
    
    #check if last last page or not
    try:
        Next_button=page_soup.findAll("a", {"class": "gradient_half"})[-2].text
    except IndexError:
        Next_button = 'null'
   
    if Next_button == "›":
        partial_url=page_soup.findAll("a", {"class": "gradient_half"})[-2].get("href")
        #print(Next_button)
        #print(partial_url)
        page_url=partial_url
        print(page_url)
        parse_page(page_url,count)    
    
parse_page(page_url,0)
print("Import from website: "+ base_url + " finished with success")   
f.close()  # Close the file
