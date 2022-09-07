
let logoList = [
	'asset/image/apple.jpg',
	'asset/image/samsung.jpg',
	'asset/image/huawei.jpg',
	'asset/image/xiaomi.jpg',
	'asset/image/sony.jpg',
	'asset/image/lg.jpg',
	'asset/image/oneplus.jpg',
]

let manufacturers = document.getElementById('manufacturers')

function setImage() {
	let innerManufacturer = ''
	logoList.forEach(logoImage => {
		innerManufacturer += '<div><img src="' + logoImage + '"></div>\n'
	});
	return innerManufacturer;
}

manufacturers.innerHTML = setImage()