document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('.form');

    const usernameInput = form.querySelector('input[name="username"]');
    const passwordInput = form.querySelector('input[name="password"]');
    const emailInput = form.querySelector('input[name="email"]');
    const roleInput = form.querySelector('select[name="role"]');
    const birthDateInput = form.querySelector('input[name="birthDate"]');
    const firstNameInput = form.querySelector('input[name="firstName"]');
    const lastNameInput = form.querySelector('input[name="lastName"]');
    const genderInput = form.querySelector('select[name="gender"]');
    const phoneNumberInput = form.querySelector('input[name="phoneNumber"]');

    // Поля для учителя
    const descriptionInput = form.querySelector('input[name="description"]');
    const yearsOfExperienceInput = form.querySelector('input[name="yearsOfExperience"]');
    const specializationInput = form.querySelector('input[name="specialization"]');
    const pricePerHourInput = form.querySelector('input[name="pricePerHour"]');

    // Поля для ученика
    const educationalInstitutionInput = form.querySelector('input[name="educationalInstitution"]');
    const parentsFirstNameInput = form.querySelector('input[name="parentsFirstName"]');
    const parentsLastNameInput = form.querySelector('input[name="parentsLastName"]');


    function showInputByRole() {
        const role = roleInput.value;

        const teacherDiv = document.getElementById('teacher');
        const studentDiv = document.getElementById('student');

        if (teacherDiv) teacherDiv.style.display = 'none';
        if (studentDiv) studentDiv.style.display = 'none';

        if (role === 'Учитель' && teacherDiv) {
            teacherDiv.style.display = 'block';
        } else if (role === 'Ученик' && studentDiv) {
            studentDiv.style.display = 'block';
        }
    }

    function showError(divId, message) {
        const div = document.getElementById(divId);
        if (div) {
            div.classList.remove('hidden');
            div.textContent = message;
            div.style.color = 'red';
            div.style.fontSize = '12px';
            div.style.marginTop = '-10px';
            div.style.marginBottom = '10px';
        }
    }

    function hideDivs() {
        const errorDivs = ['username-error-div', 'password-error-div', 'email-error-div',
            'role-error-div', 'birthDate-error-div', 'firstName-error-div',
            'lastName-error-div', 'gender-error-div', 'form-error-div', 'phone-error-div'];
        errorDivs.forEach(divId => {
            const div = document.getElementById(divId);
            if (div) div.textContent = '';
        });
    }

    roleInput.addEventListener('change', showInputByRole);
    showInputByRole();

    form.addEventListener('submit', async (event) => {
        event.preventDefault();
        event.stopPropagation();
        hideDivs();

        let isValid = true;
        const username = usernameInput.value.trim();
        const password = passwordInput.value;
        const email = emailInput.value.trim();
        const role = roleInput.value;
        const birthDateString = birthDateInput.value;
        const firstName = firstNameInput.value.trim();
        const lastName = lastNameInput.value.trim();
        const gender = genderInput.value;
        const phone = phoneNumberInput.value;

        // Валидация
        if (!username) {
            showError('username-error-div', 'Введите логин');
            isValid = false;
        }
        if (!password) {
            showError('password-error-div', 'Введите пароль');
            isValid = false;
        }
        if (!email) {
            showError('email-error-div', 'Введите email');
            isValid = false;
        }
        if (role === 'Админ') {
            showError('role-error-div', 'Выберите роль (Учитель или Ученик)');
            isValid = false;
        }
        if (!birthDateString) {
            showError('birthDate-error-div', 'Введите дату рождения');
            isValid = false;
        }
        if (!firstName) {
            showError('firstName-error-div', 'Введите имя');
            isValid = false;
        }
        if (!lastName) {
            showError('lastName-error-div', 'Введите фамилию');
            isValid = false;
        }

        if (!phone) {
            showError('phone-error-div', "Введите номер телефона")
        }

        if (!isValid) {
            return;
        }

        let birthDate = null;
        if (birthDateString && birthDateString.includes('.')) {
            const parts = birthDateString.split('.');
            if (parts.length === 3) {
                birthDate = `${parts[2]}-${parts[1]}-${parts[0]}`;
            }
        }

        const formData = {
            username: username,
            password: password,
            email: email,
            role: role,
            birthDate: birthDate,
            firstName: firstName,
            lastName: lastName,
            gender: gender,
            phoneNumber: phone
        };

        if (role === 'Учитель') {
            formData.description = descriptionInput ? descriptionInput.value : null;
            formData.yearsOfExperience = yearsOfExperienceInput ? parseInt(yearsOfExperienceInput.value) : null;
            formData.specialization = specializationInput ? specializationInput.value : null;
            formData.pricePerHour = pricePerHourInput ? parseInt(pricePerHourInput.value) : null;
        }

        if (role === 'Ученик') {
            formData.educationalInstitution = educationalInstitutionInput ? educationalInstitutionInput.value : null;
            formData.parentsFirstName = parentsFirstNameInput ? parentsFirstNameInput.value : null;
            formData.parentsLastName = parentsLastNameInput ? parentsLastNameInput.value : null;
        }

        console.log('Отправка данных:', formData);

        try {
            const response = await fetch('/api/signUp', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            });

            if (response.ok) {
                window.location.href = '/signIn';
            } else {
                const errorText = await response.text();
                showError('form-error-div', errorText || 'Ошибка при регистрации');
            }
        } catch (error) {
            console.error("Ошибка!", error);
            showError('form-error-div', 'Ошибка соединения с сервером');
        }
    });
});