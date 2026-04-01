// Code Syntax Highlighting (Basic)
function highlightCode() {
    document.querySelectorAll('pre code').forEach((block) => {
        const language = block.closest('.snippet-card')?.querySelector('.lang-badge')?.textContent.toLowerCase() || 'plaintext';
        block.className = `language-${language}`;
    });
}

// Search Functionality
function setupSearch() {
    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.addEventListener('input', function(e) {
            const term = e.target.value.toLowerCase();
            document.querySelectorAll('.snippet-card').forEach(card => {
                const title = card.querySelector('.card-title').textContent.toLowerCase();
                const description = card.querySelector('.card-text').textContent.toLowerCase();
                card.style.display = (title.includes(term) || description.includes(term)) ? 'block' : 'none';
            });
        });
    }
}

// Copy to Clipboard
function setupCopyButtons() {
    document.querySelectorAll('.copy-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const code = this.closest('.snippet-card').querySelector('code').textContent;
            navigator.clipboard.writeText(code).then(() => {
                const originalText = this.innerHTML;
                this.innerHTML = '<i class="fas fa-check"></i> Copied!';
                setTimeout(() => {
                    this.innerHTML = originalText;
                }, 2000);
            });
        });
    });
}

// Initialize when page loads
document.addEventListener('DOMContentLoaded', function() {
    highlightCode();
    setupSearch();
    setupCopyButtons();
});