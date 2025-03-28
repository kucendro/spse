function goToLanding() {
    document.getElementById("contentFrame").src = "pages/landing.html";
}

function showCart() {

}

function showSearch() {

}

function foodBar() {
    if (document.getElementById("contentFrame").src.endsWith("pages/food.html")) {
        document.querySelector(".second").innerHTML = "<h2>MENU / TANDORI / CHICKEN TANDORI</h2>";
    }
}


let cart = 0;
window.parent.document.getElementById("cart").innerHTML = "CART " + "(" + cart + ")";

function addToCart() {
    cart++;
    console.log(cart);
    window.parent.document.getElementById("cart").innerHTML = "CART " + "(" + cart + ")";
    confirm("Food added to cart! Click on the cart to view your order. 🥰 Would you like to continue shopping?");
    if (confirm) {
        window.parent.document.getElementById("contentFrame").src = "pages/menu.html";
    }
}
 function elon() {
    document.querySelector(".elon").innerHTML = '<img src="../assigments/img/elon.jpg" alt="elon" style="width: 100%; height: 100%;">';
 }
