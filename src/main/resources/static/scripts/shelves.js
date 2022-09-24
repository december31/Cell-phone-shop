class DirectButton {
	prevBtn
	nextBtn
	counter
	products

	constructor(shelves_content, prevBtn, nextBtn, counter, cardWidth, products) {
		this.prevBtn = prevBtn
		this.nextBtn = nextBtn
		this.counter = counter
		this.products = products

		shelves_content.style.transition = '0.4s'

		nextBtn.onclick = () => {
			let max = (role === "admin" || role === "editor") ? products.length - 4 : products.length - 5;
			if (counter >= max) return
			counter++;
			shelves_content.style.transform = `translateX(${-(cardWidth + 32) * counter}px)`
		}

		prevBtn.onclick = () => {
			if (counter <= 0) return
			counter--;
			shelves_content.style.transform = `translateX(${-(cardWidth + 32) * counter}px)`
		}
	}
}

let defaultProduct = {
	"id": 0,
	"name": "",
	"imageUrl": "",
	"price": 0,
	"description": "",
	"ratting": 4.5
}

let role;

// ADMIN FIELD -- START
// delete a product

let loader = document.getElementById('loader');

let setupEditAndDeleteButtons = () => {
	let deleteConfirmation = document.querySelector('#delete-confirm')
	let no = deleteConfirmation.querySelector('input:first-child')
	let yes = deleteConfirmation.querySelector('input:last-child')
	let editAndDeleteButtons = document.querySelectorAll('.edit-and-delete-button-container')
	let id;

	editAndDeleteButtons.forEach(editAnDeleteButton => {
		let deleteButton = editAnDeleteButton.querySelector('.delete-button')
		deleteButton.onclick = () => {
			id = editAnDeleteButton.querySelector('p').innerText;
			if (deleteConfirmation.style.display === 'none') {
				deleteConfirmation.style.display = 'flex'
			}
		}
	})

	document.addEventListener('keydown', (e) => {
		if (e.key === 'Escape' && deleteConfirmation.style.display !== 'none') {
			deleteConfirmation.style.display = 'none'
		}
	})

	no.onclick = () => {
		if (deleteConfirmation.style.display !== 'none') {
			deleteConfirmation.style.display = 'none'
		}
	}

	yes.onclick = () => {
		if (loader.style.display === 'none') {
			loader.style.display = 'flex'
			deleteConfirmation.style.display = 'none'
		}

		let request = new XMLHttpRequest();
		let response;
		request.open('post', 'product/delete-product', true);
		request.setRequestHeader('id', id)
		request.send()
		request.onreadystatechange = () => {
			if (request.readyState === 4) {
				response = request.responseText;
				setTimeout(() => {
					loader.style.display = 'none'
					location.reload();
				}, 1000);
			}
		}
	}
}

// set up add new product button
let addNewPrdBtn = (shelves_content) => {
	let addNewPrdForm = document.getElementById("add-new-product-form")
	let exitAddNewFormBtn = document.querySelector('#add-new-product-form i')
	addNewPrdForm.style.display = 'none'

	// add new product button
	shelves_content.forEach(shelve => {
		let addNewPrdContainer = shelve.firstChild
		let addNewPrdContent = addNewPrdContainer.children

		// turn the first product in shelve content to an add new product button
		// make all the child element in first product content disappear
		for (let i = 0; i < addNewPrdContent.length; i++) {
			addNewPrdContent[i].style.opacity = '0'
		}

		let addNewPrdBtn = document.createElement('div')
		addNewPrdBtn.innerHTML = `<i class="fa-solid fa-plus"></i>`
		addNewPrdBtn.className = 'add-new-product';

		let id = shelve.querySelector(".category-id").innerText;

		addNewPrdBtn.onclick = () => {
			addNewPrdForm.style.display = 'block'
			addNewPrdForm.removeAttribute("class");
			addNewPrdForm.classList.add(id);
		}

		addNewPrdBtn.title = 'add new product'

		addNewPrdContainer.appendChild(addNewPrdBtn)
	})

	let addNewProductSubmitButton = addNewPrdForm.querySelector("#submit");
	addNewProductSubmitButton.onclick = () => {
		let form = addNewPrdForm.querySelector('form');
		let data = new FormData(form);
		data.append("token", getToken());
		data.append("category-id", addNewPrdForm.className);
		$.ajax({
			url: "product/new",
			type: "POST",
			enctype: "multipart/form-data",
			data: data,
			cache: false,
			contentType: false,
			processData: false,
			success: (response) => {
				console.log(response);
				location.reload();
			}
		})
	}

	document.addEventListener('keydown', (e) => {
		if (e.key === 'Escape' && addNewPrdForm.style.display !== 'none') {
			addNewPrdForm.style.display = 'none'
		}
	})

	exitAddNewFormBtn.onclick = () => {
		addNewPrdForm.style.display = 'none'
	}
}

// ADMIN FIELD --- END


// GENERIC FIELD
let setListOfDirBtnAndItsAction = (shelve, products) => {
	let directButtons = []

	let card = shelve.querySelector('.shelve-content .card-container');
	let cardWidth = card.clientWidth;
	let counter = 0;

	let prevBtn = shelve.children[0];
	let nextBtn = shelve.children[1];
	let shelves_content = shelve.lastElementChild;

	directButtons.push(new DirectButton(shelves_content, prevBtn, nextBtn, counter, cardWidth, products))

	return directButtons
}

let setInnerHtml = (fatherElement, products) => {
	if (products.length === 0 && (role === 'admin' || role === "editor")) products.push(defaultProduct);
	products.forEach(product => {
		let cardContainer = document.createElement('div');

		let productPhoto = document.createElement('div')
		let image = document.createElement('img')
		image.src = product.imageUrl
		image.alt = ''

		let productName = document.createElement('div')
		let name_h2 = document.createElement('h2')
		name_h2.innerText = product.name

		let description = document.createElement('div')
		let description_p = document.createElement('p')
		description_p.innerText = product.description

		let price = document.createElement('div')
		let price_p = document.createElement('p')
		price_p.innerText = product.price.toLocaleString() + 'đ'

		let rating = document.createElement('div')

		cardContainer.className = 'card-container';
		productPhoto.className = 'photo';
		productName.className = 'name';
		price.className = 'price';
		description.className = 'description';
		rating.className = 'rating';

		productPhoto.appendChild(image)
		productName.appendChild(name_h2)
		description.appendChild(description_p)
		price.appendChild(price_p)
		rating.innerHTML = `<i class="fa-solid fa-star"></i>
		<i class="fa-solid fa-star"></i>
		<i class="fa-solid fa-star"></i>
		<i class="fa-solid fa-star"></i>
		<i class="fa-solid fa-star"></i>`

		let editAndDeleteBtnContainer;
		let editButton;
		let deleteButton;
		let id;
		if (role === 'admin' || role === "editor") {
			// ADMIN FIELD --- START
			// edit and delete button
			editAndDeleteBtnContainer = document.createElement('div')
			editAndDeleteBtnContainer.className = 'edit-and-delete-button-container'

			editButton = document.createElement('div')
			editButton.className = 'edit-button'
			editButton.innerHTML = `<i class="fa-solid fa-pencil"></i>`
			editButton.title = 'chỉnh sửa'

			deleteButton = document.createElement('div')
			deleteButton.className = 'delete-button'
			deleteButton.innerHTML = `<i class="fa-solid fa-trash-can"></i>`
			deleteButton.title = 'xóa sản phẩm'

			// save id to a p tag
			id = document.createElement('p')
			id.className = 'id'
			id.innerText = product.id
			id.style.display = 'none'

			editAndDeleteBtnContainer.append(editButton, deleteButton, id)
			// ADMIN FIELD --- END
			cardContainer.append(productPhoto, productName, price, description, rating, editAndDeleteBtnContainer)
		} else {
			cardContainer.append(productPhoto, productName, price, description, rating)
		}

		fatherElement.appendChild(cardContainer)
	});

	if ((role === "admin" || role === "editor")) {
		if (products.length === 1 && products[0] === defaultProduct) return;
		// the first element will be placed by the add new button
		let clone = fatherElement.firstChild.cloneNode(true);
		fatherElement.insertBefore(clone, fatherElement.firstChild)
	}
}

let setShelvesContent = (allProducts) => {
	let categories = getCategory();
	let shelve = document.createElement('div');
	shelve.classList.add("shelves");
	shelve.innerHTML = `
		<button type="button" class="previous-btn">
			<i class="fa-solid fa-angle-left"></i>
		</button>
		<button type="button" class="next-btn">	
			<i class="fa-solid fa-angle-right"></i>
		</button>
		<div class="title">
			<h1>bán chạy nhất</h1>
		</div>
		<div class="shelve-content"></div>
	`
	let shelves_content = [];
	let shelves_container = document.querySelector(".shelves-container");
	categories.forEach(category => {

		let shelveClone = shelve.cloneNode(true);
		shelveClone.classList.add(category.name.replaceAll(" ", "-"));
		shelveClone.querySelector(".title h1").innerText = category.name;

		let products = allProducts.filter(product => {
			return product.category.name === category.name;
		});

		console.log(products);

		let shelve_content = shelveClone.querySelector('.shelve-content');

		setInnerHtml(shelve_content, products);

		let categoryId = document.createElement("p")
		categoryId.innerText = category.id;
		categoryId.className = "category-id";
		categoryId.style.display = "none";
		shelve_content.appendChild(categoryId);

		shelves_content.push(shelve_content);

		shelves_container.appendChild(shelveClone);

		setListOfDirBtnAndItsAction(shelveClone, products)
	});
	return shelves_content;
}

let pushDataToHtml = (products) => {
	let shelves_content = setShelvesContent(products)
	if (role === "admin" || role === "editor") {
		addNewPrdBtn(shelves_content);
		setupEditAndDeleteButtons();
	}
}

let getDataFromServer = () => {
	let request = new XMLHttpRequest()
	request.open("get", "product", true)
	request.send()
	request.onreadystatechange = () => {
		if (request.readyState === XMLHttpRequest.DONE) {
			if (request.status === 200) {
				setTimeout(() => {
					loader.style.display = 'none'
				}, 1000);
				let products = JSON.parse(request.responseText);
				pushDataToHtml(products)
			}
		}
	}
}

let checkAccountStoredInCookie = () => {
	loader.style.display = 'flex'
	loader.style.backgroundColor = '#fff'

	let token = getToken();

	$.ajax({
		type: "GET",
		url: "user/auto-sign-in",
		data: {
			"token": token
		},
		success: function (response) {
			if (response !== "failed") {
				let username = document.querySelector('#account h4')
				let email = response.split(":")[1].split("@")[0];
				username.innerText = `${email}`
				role = response.split(":")[0];
				if (role === "admin") {
					showAdminFeature();
				}
			}
			getDataFromServer()
		}
	});
}

let getToken = () => {
	let cookies = document.cookie.split(";");
	for (let i = 0; i < cookies[i].length; i++) {
		let name = cookies[i].split("=")[0];
		let value = cookies[i].split("=")[1];
		if (name === "token") {
			return value;
		}
	}
	return null;
}

function getCategory() {
	let res;
	$.ajax({
		type: "GET",
		url: "category",
		async: false,
		success: function (response) {
			res = response;
		}
	})
	return res;
}

checkAccountStoredInCookie()

// prevent resubmit when refresh page
if (window.history.replaceState) {
	window.history.replaceState(null, null, window.location.href)
}

