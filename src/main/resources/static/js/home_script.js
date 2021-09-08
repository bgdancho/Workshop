'use strict'



window.addEventListener('load', function(event) {

const partsButton= document.querySelector('.header__parts');
const ordersButton = document.querySelector('.header__orders');

const overlay = document.querySelector('.overlay');
const mainImage = document.querySelector('.main_image');

const closeParts = document.querySelector('.closeParts');
const closeOrders = document.querySelector('.closeOrders');

partsButton.addEventListener('click',function(){
    show("parts")
});
ordersButton.addEventListener('click',function(){
    show("orders");
});


const show = function(menuName){
    let newMenu = document.querySelector('.header__' + menuName + '__menu');
    close();

        overlay.classList.remove('fade-out');
        newMenu.classList.remove('fade-out');
        if(mainImage != null){
                mainImage.style.opacity ="0.1";
        }
        overlay.classList.add('fade-in');
        newMenu.classList.add('fade-in');
}
closeParts.addEventListener('click',function(){
        close();
})
closeOrders.addEventListener('click',function(){
    close();
});

const close = () => {
    document.querySelector('.header__parts__menu').classList.add('fade-out');
    document.querySelector('.header__orders__menu').classList.add('fade-out');
    overlay.classList.remove('fade-in');
    overlay.classList.add('fade-out');

     if(mainImage != null){
                     mainImage.style.opacity="0.8";
                     };
}
window.onclick = function(event) {
	if (event.target == overlay) {
        close();
	}
}
});
