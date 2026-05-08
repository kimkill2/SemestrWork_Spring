document.addEventListener("DOMContentLoaded", init);

const API_BASE_URL = '/api/sms';

let codeInput;
let verifyBtn;
let messageDiv;
let csrfInput;
let csrfValue;

function showMessage(text, type) {
    if (!messageDiv) return;
    messageDiv.textContent = text;
    messageDiv.className = `message ${type}`;
}

async function sendCodeOnLoad() {
    try {
        showMessage('Отправка кода...', 'info');

        const response = await fetch(`${API_BASE_URL}/sendCode`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfValue
            }
        });

        // Проверяем Content-Type ответа
        const contentType = response.headers.get("content-type");

        if (response.ok) {
            let message;
            if (contentType && contentType.includes("application/json")) {
                const data = await response.json();
                message = data.message || data;
            } else {
                message = await response.text();
            }
            showMessage(`✓ ${message}`, 'success');
            console.log("Код отправлен успешно");
        } else {
            let errorMessage;
            if (contentType && contentType.includes("application/json")) {
                const data = await response.json();
                errorMessage = data.message || data;
            } else {
                errorMessage = await response.text();
            }
            showMessage(`✗ ${errorMessage || 'Ошибка отправки кода'}`, 'error');
            console.log("Ошибка отправки кода");
        }
    } catch (error) {
        console.error("Ошибка:", error);
        showMessage('✗ Ошибка соединения с сервером', 'error');
    }
}

async function verifyCode() {
    const code = codeInput.value.trim();

    if (!code) {
        showMessage('✗ Введите код', 'error');
        codeInput.focus();
        return;
    }

    // Блокируем кнопку
    verifyBtn.disabled = true;
    verifyBtn.textContent = 'Проверка...';
    showMessage('Проверка кода...', 'info');

    try {
        const response = await fetch(`${API_BASE_URL}/verify`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfValue
            },
            body: JSON.stringify({ code: code })
        });

        const contentType = response.headers.get("content-type");

        if (response.ok) {
            let message;
            if (contentType && contentType.includes("application/json")) {
                const data = await response.json();
                message = data.message || data;
            } else {
                message = await response.text();
            }
            showMessage(`✓ ${message}`, 'success');

            setTimeout(() => {
                window.location.href = '/restorePassword';
            }, 1500);
        } else {
            let errorMessage;
            if (contentType && contentType.includes("application/json")) {
                const data = await response.json();
                errorMessage = data.message || data;
            } else {
                errorMessage = await response.text();
            }
            showMessage(`✗ ${errorMessage || 'Неверный код'}`, 'error');
            verifyBtn.disabled = false;
            verifyBtn.textContent = 'Подтвердить';
            codeInput.focus();
        }
    } catch (error) {
        console.error('Ошибка:', error);
        showMessage('✗ Ошибка соединения с сервером', 'error');
        verifyBtn.disabled = false;
        verifyBtn.textContent = 'Подтвердить';
    }
}

function init() {
    codeInput = document.getElementById('code');
    verifyBtn = document.getElementById('verifyBtn');
    csrfInput = document.getElementById('csrf');
    messageDiv = document.getElementById('message');

    if (!codeInput || !verifyBtn || !messageDiv || !csrfInput) {
        console.error('Не удалось найти необходимые элементы DOM');
        return;
    }

    csrfValue = csrfInput.value;

    sendCodeOnLoad();
    verifyBtn.addEventListener('click', verifyCode);

    // Добавляем отправку по Enter
    codeInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            verifyCode();
        }
    });
}