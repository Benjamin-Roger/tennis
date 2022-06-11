/** @type {import('tailwindcss').Config} */
module.exports = {
    content: [
        "./src/**/*.{js,jsx,ts,tsx}",
    ],
    theme: {
        colors: {
            primary: "var(--primary)",
            secondary: "var(--secondary)",
            black: "var(--black)",
            white: "var(--white)"
        },
        extend: {
            boxShadow: {
                "custom": ".3rem .3rem .3rem var(--black)",
            }
        },
    },
    plugins: [],
}
