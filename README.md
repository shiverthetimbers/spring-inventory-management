
#Dalton Wood

#ID: 012123139


C.  Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.


Note: Do not remove any elements that were included in the screen. You may add any additional elements you would like or any images, colors, and styles, although it is not required.

**Changes for C**
* mainscreen.html
  * lines 14, 19, 21, and 53

D.  Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen.

**Changes for D**
* Created about.html
* Created AboutController.java
* Added anchor element to mainscreen.html
  * line 20

E.  Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database.


Note: Make sure the sample inventory is added only when both the part and product lists are empty. When adding the sample inventory appropriate for the store, the inventory is stored in a set so duplicate items cannot be added to your products. When duplicate items are added, make a “multi-pack” part.

**Changes for E**
* Added parameterized constructor to InhousePart.java
  * lines 19-24
* Added default parts and products to BootStrapData.java
  * lines 43-67

F.  Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:
•  The “Buy Now” button must be next to the buttons that update and delete products.
• The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.
•  Display a message that indicates the success or failure of a purchase.

**Changes for F**
* Added 'Buy Now' button to mainscreen.html
  * lines 83-85
* Added buyProduct() method to AddProductController.java
  * lines 129-145
* Created confirmationBuyProduct.html
* Created failureBuyProduct.html

G.  Modify the parts to track maximum and minimum inventory by doing the following:
•  Add additional fields to the part entity for maximum and minimum inventory.
•  Modify the sample inventory to include the maximum and minimum fields.
•  Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
•  Rename the file the persistent storage is saved to.
•  Modify the code to enforce that the inventory is between or at the minimum and maximum value.

**Changes for G**
* Part.java
  * Added field variables for minimum and maximum (lines 35-38)
  * Added annotation for new validator (line 21)
  * Added new variables to constructors (lines 52-53, 61-62)
  * Added getters and setters for new variables (lines 97-112)
* InhousePart.java
  * Added new variables to constructor (lines 20-21)
* OutsourcedPart.java
  * Added new variables to constructor (lines 20-23)
* BootStrapData.java
  * Modified sample inventory to include min and max parameters (lines 47-51)
* InhousePartForm.html
  * Added form fields for new variables (lines 30-38)
  * Added error block for inventory validation (lines 40-44)
* OursorcedPartForm.html
  * Added form fields for new variables (lines 25-29)
  * Added error block for inventory validation (lines 31-35)
* Mainscreen.html
  * Added columns and headers for new fields (lines 39-40, 49-50)
* Application.properties
  * renamed file for persistent storage (line 6)
* Created ValidInventory.java
* Created InventoryValidator.java

H.  Add validation for between or at the maximum and minimum fields. The validation must include the following:
•  Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
•  Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
•  Display error messages when adding and updating parts if the inventory is greater than the maximum.

**Changes for H**
* AddProductController.java
  * Uncommented code (lines 94, 153)
* application.properties
  * Changed database filename (line 6)
* EnufPartsValidator.java
  * Changed condition logic to account for part minimum inventory (lines 36-40)
* Logic for validating part inventory being between minimum and maximum already in place (see previous commit)

I.  Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.

**Changes for I**
* PartTest.java
  * Added test methods for minInv and maxInv getters and setters (lines 103-138)

J.  Remove the class files for any unused validators in order to clean your code.

**Changes for J**
* No changes. All validators still used.