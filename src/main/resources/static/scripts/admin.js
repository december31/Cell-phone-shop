const editUser = 0
const editCategory = 1;

let userCounter = 1;
let categoryCounter = 1;

const userEditView = document.createElement('div');
userEditView.classList.add("user", "edit-field")
userEditView.style.transform = "scale(0)";
userEditView.innerHTML = `
	<div class="user edit-field-container">
		<i class="fa-solid fa-x exit"></i>
		<div class="list">
			<div class="title">
				<h3 class="text-center">User Edit</h3>
			</div>
			<div class="list-container">
				<div class="table">
					<div class="table-header all-border">
						<div class="index col-1 text-center border-right"><p>INDEX</p></div>
						<div class="email col-6 text-center border-right"><p>EMAIL</p></div>
						<div class="password col-3 text-center border-right"><p>PASSWORD</p></div>
						<div class="role col-2 text-center border-right border-right"><p>ROLE</p></div>
						<i class="edit fa-solid fa-pencil" style="opacity: 0;"></i>
						<i class="delete fa-solid fa-trash-can" style="opacity: 0;"></i>
					</div>
					<div class="item-information">
						<div class="index col-1"><input class="col-12 text-center" type="text" name="" id="index-filter" placeholder="filter" autocomplete="off"></div>
						<div class="email col-6"><input class="col-12" type="text" name="" id="email-filter" placeholder="filter" autocomplete="off"></div>
						<div class="password col-3"><input class="col-12" type="password" value="" id="password-filter" style="background-color: transparent;" disabled></div>
						<div class="role col-2"><select class="col-12" name="role" id="role-filter">
						<option value="-1" selected>all</option>
							<option value="0">admin</option>
							<option value="1">editor</option>
							<option value="2">user</option>
						</select></div>
						<i class="fa-solid fa-pencil border-top" style="opacity: 0;"></i>
						<i class="fa-solid fa-trash-can border-top" style="opacity: 0;"></i>
					</div>
					<div class="item-information">
						<div class="index col-1"><input class="col-12 text-center" type="text" name="" id="new-user-index" value="" disabled></div>
						<div class="email col-6"><input class="col-12" type="text" name="" id="new-user-email" value="" disabled></div>
						<div class="password col-3"><input class="col-12" type="password" id="new-user-password" value="" disabled></div>
						<div class="role col-2"><select class="col-12" name="role" id="" disabled>
							<option value="0">admin</option>
							<option value="1">editor</option>
							<option value="2" selected>user</option>
						</select></div>
						<i class="new fa-solid fa-circle-plus"></i>
						<i class="fa-solid fa-circle-plus" style="opacity: 0;"></i>
					</div>
				</div>
			</div>
			<div class="button-field">
				<input type="button" name="" id="users-save" value="save">
				<input type="button" name="" id="users-cancel" value="cancel">
			</div>
		</div>
	</div>
	`

const categoryEditView = document.createElement("div");
categoryEditView.classList.add("category", "edit-field");
categoryEditView.style.transform = "scale(0)"
categoryEditView.innerHTML = `
<div class="category edit-field-container">
    <i class="fa-solid fa-x exit"></i>
    <div class="list">
        <div class="title">
            <h3 class="text-center">category edit</h3>
        </div>
        <div class="list-container">
            <div class="table">
                <div class="table-header all-border">
                    <div class="index col-1 text-center border-right"><p>index</p></div>
                    <div class="id col-1 text-center border-right"><p>id</p></div>
                    <div class="name col-10 text-center border-right"><p>name</p></div>
                    <i class="edit fa-solid fa-pencil" style="opacity: 0;"></i>
                    <i class="delete fa-solid fa-trash-can" style="opacity: 0;"></i>
                </div>
                <div class="item-information">
                    <div class="index col-1"><input class="col-12 text-center" type="text" name="" id="index-filer"
                                                    placeholder="filter"></div>
                    <div class="id col-1"><input class="col-12 text-center" type="text" name="" id="id-filter"
                                                    placeholder="filter"></div>
                    <div class="name col-10"><input class="col-12" type="text" name="" id="name-filter" placeholder="filter"></div>
                    <i class="fa-solid fa-pencil border-top" style="opacity: 0;"></i>
                    <i class="fa-solid fa-trash-can border-top" style="opacity: 0;"></i>
                </div>
                <div class="item-information">
                    <div class="index col-1"><input class="col-12 text-center" type="text" name="" id="new-index" value=""
                                                    disabled></div>
                    <div class="id col-1"><input class="col-12 text-center" type="text" name="" id="new-id" value=""
                                                    disabled></div>
                    <div class="name col-10"><input class="col-12" type="text" name="" id="new-name" value="" disabled></div>
                    <i class="new fa-solid fa-circle-plus"></i>
                    <i class="new fa-solid fa-circle-plus" style="opacity: 0;"></i>
                </div>
            </div>
        </div>
        <div class="button-field">
            <input type="button" name="" id="" value="save">
            <input type="button" name="" id="" value="cancel">
        </div>
    </div>
</div>
`

let showAdminFeature = () => {
	let container = document.createElement('div')
	container.innerHTML =
		`<div class="admin-feature-button">
				<i class="fa-solid fa-pencil"></i>
				<div class="admin-feature-container">
					<ul>
						<li name="edit-user">Edit user</li>
						<li name="edit-category">Edit category</li>
					</ul>
				</div>
			</div>`;

	document.querySelector("body").appendChild(userEditView);
	document.querySelector("body").appendChild(categoryEditView);
	let adminFeatureButton = container.querySelector('.admin-feature-button')
	let adminFeatureContainer = container.querySelector(".admin-feature-container")
	let editUserButton = container.querySelector("[name='edit-user']")
	let editCategoryButton = container.querySelector("[name='edit-category']")
	let icon = adminFeatureButton.querySelector(".admin-feature-button i")

	getJson(editUser);
	getJson(editCategory);

	let closeAdminFeature = () => {
		if (icon.style.transform === "rotateZ(360deg)") {
			icon.style.transform = "rotateZ(0)"
			adminFeatureButton.style.backgroundColor = "#0ff"
			adminFeatureContainer.style.animation = "sink 0.5s"
			adminFeatureContainer.style.transform = "scale(0)"
			adminFeatureContainer.style.top = "-40px"
			adminFeatureContainer.style.left = "-40px"
			return true;
		}
		return false;
	}

	adminFeatureButton.onclick = () => {
		if (!closeAdminFeature()) {
			icon.style.transform = "rotateZ(360deg)"
			adminFeatureButton.style.backgroundColor = "#FFE7A4"
			adminFeatureContainer.style.animation = "grow-up 0.5s"
			adminFeatureContainer.style.transform = "scale(1)"
			adminFeatureContainer.style.top = "-100px"
			adminFeatureContainer.style.left = "-100px"

		}
	}

	document.addEventListener('keydown', (even) => {
		if (even.key === "Escape") {
			closeAdminFeature();
			closeFeature(editUser);
			closeFeature(editCategory);
		}
	})
	document.querySelector('body').appendChild(adminFeatureButton)

	editUserButton.onclick = () => {
		showFeature(editUser);
	}

	editCategoryButton.onclick = () => {
		showFeature(editCategory);
	}
}

let showFeature = (action) => {
	let editField = null;
	let exitButton = null;
	let cancelButton = null;
	let saveButton = null;

	if (action === editUser) {
		editField = document.querySelector(".user.edit-field");
		closeFeature(editCategory);
	} else if (action === editCategory) {
		editField = document.querySelector(".category.edit-field");
		closeFeature(editUser);
	}

	if (editField !== null) {
		exitButton = editField.querySelector(".edit-field-container>i");
		cancelButton = editField.querySelector("[value='cancel']");
		saveButton = editField.querySelector("[value='save']");

		if (editField.style.transform === "scale(0)") {
			editField.style.transform = "scale(1)"
			editField.style.animation = "grow-up-normal 0.5s"
		} else {
			closeFeature(action)
		}

		exitButton.onclick = cancelButton.onclick = () => {
			closeFeature(action)
		}

		saveButton.onclick = () => {
			if (action === editUser) saveUser();
			else if (action === editCategory) saveCategory();
		}
	}

}

let closeFeature = (action) => {
	let editField = null;
	if (action === editUser) {
		editField = document.querySelector('.user.edit-field');
	} else if (action === editCategory) {
		editField = document.querySelector('.category.edit-field');
	}
	if (editField !== null) {
		if (editField.style.transform === "scale(1)") {
			editField.style.transform = "scale(0)"
			editField.style.animation = "sink-normal 0.5s"
		}
	}
}

let setupUsersData = (data) => {
	if (data.length === 0) return;
	if (!Array.isArray(data)) return;
	let parentElement = userEditView.querySelector(".table");
	let lastElement = parentElement.querySelector(".item-information:last-child");
	data.forEach(user => {
		parentElement.insertBefore(buildUserElement(user), lastElement);
	});

	let addNewUser = userEditView.querySelector("i.new");
	addNewUser.onclick = () => {
		parentElement.insertBefore(buildUserElement(), lastElement);
	}
}

let setupCategoryData = (data) => {
	if (data.length === 0) return;
	if (!Array.isArray(data)) return;
	let parentElement = categoryEditView.querySelector(".table")
	let lastElement = parentElement.querySelector(".item-information:last-child");
	data.forEach(category => {
		parentElement.insertBefore(buildCategoryElement(category), lastElement);
	});

	let addNewCategory = categoryEditView.querySelector("i.new");
	addNewCategory.onclick = () => {
		parentElement.insertBefore(buildCategoryElement(), lastElement);
	}
}

let buildUserElement = (user = null) => {
	let userInformation = document.createElement("div");
	userInformation.className = "item-information";
	userInformation.innerHTML = `
			<div class="strike" style="display: none"></div>
			<div class="index col-1"><input class="col-12 text-center" type="text" name="" id="${userCounter}" value="${userCounter}" disabled></div>
			<div class="email col-6"><input class="col-12" type="text" name="" id="${user ? user.email : 'new' + userCounter}-email" value="${user ? user.email : ''}" disabled></div>
			<div class="password col-3"><input class="col-12" type="password" id="${user ? user.email : 'new' + userCounter}-password" value="" disabled><i class="fa-solid fa-eye"></i></div>
			<div class="role col-2"><select class="col-12" name="role" id="${user ? user.email : 'new' + userCounter}-role" disabled>
				<option value="0">admin</option>
				<option value="1">editor</option>
				<option value="2">user</option>
			</select></div>
			<i class="edit fa-solid fa-pencil border-top"></i>
			<i class="delete fa-solid fa-trash-can border-top"></i>
        `
	let role = user ? user.role.id : 2;
	roleSelector = userInformation.querySelector(".role");
	role = roleSelector.querySelector(`[value="${role}"]`);
	role.selected = true;

	let showPasswordButton = userInformation.querySelector(".password>i");

	setupItemFeature(userInformation, showPasswordButton);

	userCounter++;
	return userInformation;
}

let buildCategoryElement = (category = null) => {
	let categoryInformation = document.createElement("div");
	categoryInformation.className = "item-information";
	categoryInformation.innerHTML = `
		<div class="strike" style="display: none"></div>		
		<div class="index col-1"><input class="col-12 text-center" type="text" name="" id="${categoryCounter} - index" value="${categoryCounter}" disabled></div>	
		<div class="id col-1"><input class="col-12 text-center" type="text" name="" id="${categoryCounter} - id" value="${category ? category.id : "auto"}" disabled></div>	
		<div class="name col-10"><input class="col-12" type="text" name="" id="${categoryCounter} - name" value="${category ? category.name : ""}" disabled></div>
		<i class="edit fa-solid fa-pencil border-top"></i>	
		<i class="delete fa-solid fa-trash-can border-top"></i>
	`

	setupItemFeature(categoryInformation);
	categoryCounter++;
	return categoryInformation;

}

let setupItemFeature = (itemInformation, showPasswordButton = null) => {
	let editBtn = itemInformation.querySelector("i.edit");
	let deleteBtn = itemInformation.querySelector("i.delete");
	let inputs = itemInformation.querySelectorAll("input");
	let roleSelector = itemInformation.querySelector("select");
	let strikeLine = itemInformation.querySelector(".strike");

	if (showPasswordButton !== null) {
		showPasswordButton.style.opacity = "0.5";

		showPasswordButton.onclick = () => {
			if (inputs[2].disabled === false) {
				if (inputs[2].type === "password") {
					inputs[2].type = "text";
					showPasswordButton.classList.remove("fa-eye");
					showPasswordButton.classList.add("fa-eye-slash");
				} else {
					inputs[2].type = "password";
					showPasswordButton.classList.remove("fa-eye-slash");
					showPasswordButton.classList.add("fa-eye");
				}
			}
		}
	}

	editBtn.onclick = () => {
		if (editBtn.classList.contains("fa-pencil")) {
			editBtn.classList.remove("fa-pencil")
			editBtn.classList.add("fa-check");
			editBtn.style.backgroundColor = "#FFCD0D";
			if (showPasswordButton) showPasswordButton.style.opacity = "1";
			inputs.forEach(input => {
					input.disabled = false;
					if (showPasswordButton) roleSelector.disabled = false;
				}
			)
			inputs[0].disabled = true;
			if (!showPasswordButton) inputs[1].disabled = true;
		} else {
			editBtn.classList.add("fa-pencil");
			editBtn.classList.remove("fa-check")
			editBtn.style.removeProperty("background-color");
			if (showPasswordButton !== null) showPasswordButton.style.opacity = "0.5";
			inputs.forEach(input => {
					input.disabled = true;
					if (showPasswordButton) roleSelector.disabled = true;
				}
			)
		}
	}

	deleteBtn.onclick = () => {
		if (strikeLine.style.display === "none") {
			strikeLine.style.display = "block";
			deleteBtn.classList.remove("fa-trash-can");
			deleteBtn.classList.add("fa-arrow-rotate-left")
		} else {
			strikeLine.style.display = "none";
			deleteBtn.classList.remove("fa-arrow-rotate-left")
			deleteBtn.classList.add("fa-trash-can");
		}
	}
}

let getJson = (action) => {
	$.ajax({
		type: "GET",
		url: action === editUser ? "user" : "category",
		success: function (response) {
			if (action === editUser) setupUsersData(response);
			else if (action === editCategory) setupCategoryData(response);
		}
	})
}

let saveUser = () => {
	let users = [];
	let usersDeleted = [];
	let usersInformation = userEditView.querySelectorAll(".item-information");
	for (let i = 1; i < usersInformation.length - 1; i++) {
		let deleted = usersInformation[i].querySelector(".strike").style.display === "block";
		let email = usersInformation[i].querySelector(".email > input").value;
		let password = usersInformation[i].querySelector(".password > input").value;
		let role = usersInformation[i].querySelector(".role option:checked");
		let roleId = role.value;
		let roleName = role.innerText;
		if (deleted) {
			usersDeleted.push({
				email: email,
				password: password,
				role: {
					id: roleId,
					name: roleName
				}
			})
		} else {
			users.push({
				email: email,
				password: password,
				role: {
					id: roleId,
					name: roleName
				}
			})
		}
	}

	console.log("users: ", users)
	$.ajax({
		type: "POST",
		url: "user/save",
		data: {
			"users": JSON.stringify(users),
			"usersDeleted": JSON.stringify(usersDeleted)
		},
		dataType: "text",
		success: function (response) {
			console.log(response)
			location.reload();
		}
	})
}

let saveCategory = () => {
	let categories = [];
	let categoriesInformation = categoryEditView.querySelectorAll(".item-information");
	for (let i = 1; i < categoriesInformation.length - 1; i++) {
		let deleted = categoriesInformation[i].querySelector(".strike").style.display === "block";
		let id = categoriesInformation[i].querySelector(".id>input").value;
		let name = categoriesInformation[i].querySelector(".name>input").value;
		if (name === "") continue;
		categories.push({
			"id": id === "auto" ? -1 : id,
			"name": deleted ? "" : name
		});
	}

	$.ajax({
		type: "POST",
		url: "category/save-categories",
		data: {
			"data": JSON.stringify(categories)
		},
		success: function (response) {
			console.log(response);
			location.reload();
		}
	})
}