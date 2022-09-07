let imageList = [
	'asset/image/pic1.jpg',
	'asset/image/pic2.jpg',
	'asset/image/pic3.jpg',
	'asset/image/pic4.jpg',
	'asset/image/pic5.jpg',
]

let sliderContainer = document.getElementsByClassName('image-container')[0]

let width = sliderContainer.clientWidth
let height = sliderContainer.clientHeight

let loadImage = () => {
	let innerSliderContainer = ''
	innerSliderContainer += '<div><img style="width: '+ width +'px" src="' + imageList[imageList.length - 1] + '"></div>\n'
	imageList.forEach(imageSrc => {
		innerSliderContainer += '<div><img style="width: '+ width +'px" src="' + imageSrc + '"></div>\n'
	});
	innerSliderContainer += '<div><img style="width: '+ width +'px" src="' + imageList[0] + '"></div>\n'
	return innerSliderContainer;
}

sliderContainer.innerHTML = loadImage()


let images = document.querySelectorAll('.image-container img')
let nextBtn = document.querySelector('#slider .next-btn')
let previousBtn = document.querySelector('#slider .previous-btn')
let counter = 1

sliderContainer.style.transform = 'translate(' + -width + 'px)'

nextBtn.onclick = () => {
	if(counter >= images.length - 1) return
	counter++
	sliderContainer.style.transition = '0.4s'
	sliderContainer.style.transform = 'translate(' + -width*counter + 'px)'
}

previousBtn.onclick = () => {
	if(counter <= 0) return
	counter--
	sliderContainer.style.transform = 'translate(' + -width*(counter) + 'px)'
	sliderContainer.style.transition = '0.4s'
}

sliderContainer.addEventListener('transitionend', () => {
	if(counter === images.length - 1) {
		counter = 1
		sliderContainer.style.transition = 'none'
		sliderContainer.style.transform = 'translate(' + -width + 'px)'
	}
	
	if(counter === 0) {
		counter = images.length - 2
		sliderContainer.style.transition = 'none'
		sliderContainer.style.transform = 'translate(' + -width*counter + 'px)'
	}
})

let changePics = () => {
	if(counter >= images.length - 1) return
	counter++
	sliderContainer.style.transition = '1s ease-in-out'
	sliderContainer.style.transform = 'translate(' + -width*counter + 'px)'
}

setInterval(() => {
	changePics()
}, 4000);
