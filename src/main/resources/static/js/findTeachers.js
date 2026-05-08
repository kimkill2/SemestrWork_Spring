async function updateAllViews() {

    try {
        const response = await fetch('/api/views/today', {
            method: 'GET',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json',
            }
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();

        const containers = document.querySelectorAll('.teacher-container');

        containers.forEach((container, index) => {
            const teacherId = container.getAttribute("data-user-id");
            const viewsSpan = container.querySelector('.views-count');

            if (viewsSpan && teacherId) {
                const viewsCount = data[teacherId] || 0;
                viewsSpan.textContent = viewsCount;
            }
        });


    } catch (error) {
        console.error("Error loading views:", error);
        console.error("Error details:", error.message);
    }
}

function init() {
    updateAllViews();

    window.addEventListener('pageshow', function(event) {
        updateAllViews();
    });

    document.addEventListener('visibilitychange', function() {
        if (!document.hidden) {
            updateAllViews();
        }
    });

    setInterval(updateAllViews, 30000);
}

if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', init);
} else {
    init();
}