async function handleLoginClick() {
	const inputs = document.querySelectorAll("input");
	
	const loginUser = {
		username: inputs[0].value,
		password: inputs[1].value
	}
	
	try {
		const response = await fetch("/Product/auth/signIn", {
			method:"POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(loginUser)
		});
		if(!response.ok) {
			throw await response.json();
		}
		alert("로그인 성공")
		location.href="/Product/home.do"
	} catch(error) {
		alert(error?.errorMassage);	
	}
}