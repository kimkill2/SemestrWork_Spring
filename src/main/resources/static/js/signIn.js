document.addEventListener('DOMContentLoaded', function() {

    const form = document.querySelector(".form")
    const usernameInput = form.querySelector('input[name="username"]');
    const passwordInput = form.querySelector('input[name="password"]');


    function showError(divId, message) {
        const div = document.getElementById(divId);
        div.classList.remove('hidden');
        div.textContent = message;
    }

    function hideDivs() {
        const divs = form.querySelectorAll('div');
        divs.forEach(div => {
            div.classList.add('hidden');
            div.textContent = '';
        })
    }


    form.addEventListener('submit', async (event) => {
        event.preventDefault();
        event.stopPropagation();

        hideDivs();

        let isValid = true;
        const username = usernameInput.value.trim();
        const password = passwordInput.value.trim();

        if (!username) {
            showError('username-error-div', 'Введите логин');
            isValid = false;
        } else if (username.length <= 4) {
            showError('username-error-div', 'Логин должен быть минимум 5 символов');
            isValid = false;
        }
        if (!password) {
            showError('password-error-div', 'Введите пароль');
            isValid = false;
        } else if (password.length <= 4) {
            showError('password-error-div', 'Пароль должен быть минимум 5 символов');
            isValid = false;
        }

        if (!isValid) {
            return;
        }

        try {
            const formData = {
                'username': username,
                'password': password,
            }

            const response = await fetch('/api/signIn', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            });

            if (!response) {
                console.error('Response is undefined!');
                throw new Error('No response from server');
            }

            console.log('Response status:', response.status);
            console.log('Response headers:', response.headers.get('Content-Type'));

            const text = await response.text();
            console.log('Response body:', text);

            if (response.ok) {
                form.submit();
            } else {
                showError('form-error-div', text);
            }

        } catch (error) {
            console.error("Моя Ошибка!", error);
            showError('form-error-div', 'Ошибка соединения с сервером');
        }
    })
})



