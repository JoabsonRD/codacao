document.addEventListener("DOMContentLoaded", () => {
    const themeToggleBtn = document.getElementById("theme-toggle");

    // Verificar se o usuário prefere tema escuro no navegador
    const prefersDarkScheme = window.matchMedia("(prefers-color-scheme: dark)");

    // Carregar tema salvo no localStorage ou usar a preferência do sistema
    const currentTheme = localStorage.getItem("theme");
    if (currentTheme === "dark" || (!currentTheme && prefersDarkScheme.matches)) {
        document.documentElement.setAttribute("data-theme", "dark");
        themeToggleBtn.textContent = "☀️ Modo Claro";
    } else {
        document.documentElement.setAttribute("data-theme", "light");
        themeToggleBtn.textContent = "🌙 Modo Escuro";
    }

    // Alternar tema ao clicar no botão
    themeToggleBtn.addEventListener("click", () => {
        let theme = document.documentElement.getAttribute("data-theme");

        if (theme === "dark") {
            document.documentElement.setAttribute("data-theme", "light");
            localStorage.setItem("theme", "light");
            themeToggleBtn.textContent = "🌙 Modo Escuro";
        } else {
            document.documentElement.setAttribute("data-theme", "dark");
            localStorage.setItem("theme", "dark");
            themeToggleBtn.textContent = "☀️ Modo Claro";
        }
    });
});