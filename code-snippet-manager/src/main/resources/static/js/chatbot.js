class AIChatbot {
    constructor() {
        this.isOpen = false;
        this.messages = [];
        this.init();
    }

    init() {
        this.createChatbotHTML();
        this.bindEvents();
        this.addWelcomeMessage();
    }

    createChatbotHTML() {
        const chatbotHTML = `
            <div class="chatbot-container">
                <div class="chatbot-window">
                    <div class="chatbot-header">
                        <div class="chatbot-title">
                            <div class="chatbot-ai-icon">AI</div>
                            Code Assistant
                        </div>
                        <button class="chatbot-close">×</button>
                    </div>
                    <div class="chatbot-messages" id="chatbot-messages"></div>
                    <div class="chatbot-input-container">
                        <textarea class="chatbot-input" placeholder="Ask me to format code, explain, or help with programming..." rows="1"></textarea>
                        <div class="quick-actions">
                            <div class="quick-action" data-action="format">Format Code</div>
                            <div class="quick-action" data-action="explain">Explain Code</div>
                            <div class="quick-action" data-action="optimize">Optimize Code</div>
                            <div class="quick-action" data-action="debug">Debug Help</div>
                        </div>
                    </div>
                </div>
                <button class="chatbot-toggle">🤖</button>
            </div>
        `;
        
        document.body.insertAdjacentHTML('beforeend', chatbotHTML);
        this.messagesContainer = document.getElementById('chatbot-messages');
        this.input = document.querySelector('.chatbot-input');
        this.window = document.querySelector('.chatbot-window');
        this.toggle = document.querySelector('.chatbot-toggle');
        this.close = document.querySelector('.chatbot-close');
    }

    bindEvents() {
        this.toggle.addEventListener('click', () => this.toggleChatbot());
        this.close.addEventListener('click', () => this.toggleChatbot());
        
        this.input.addEventListener('keypress', (e) => {
            if (e.key === 'Enter' && !e.shiftKey) {
                e.preventDefault();
                this.sendMessage();
            }
        });

        this.input.addEventListener('input', () => {
            this.autoResize();
        });

        // Quick actions
        document.querySelectorAll('.quick-action').forEach(button => {
            button.addEventListener('click', (e) => {
                const action = e.target.dataset.action;
                this.handleQuickAction(action);
            });
        });

        // Close chatbot when clicking outside
        document.addEventListener('click', (e) => {
            if (this.isOpen && !e.target.closest('.chatbot-container')) {
                this.toggleChatbot();
            }
        });
    }

    toggleChatbot() {
        this.isOpen = !this.isOpen;
        this.window.classList.toggle('active', this.isOpen);
        
        if (this.isOpen) {
            this.input.focus();
        }
    }

    autoResize() {
        this.input.style.height = 'auto';
        this.input.style.height = Math.min(this.input.scrollHeight, 120) + 'px';
    }

    addWelcomeMessage() {
        const welcomeMessage = `
            Hello! I'm your AI coding assistant. I can help you with:
            • Formatting and beautifying code
            • Explaining code functionality
            • Optimizing and refactoring
            • Debugging assistance
            • Code generation
            
            Try the quick actions or ask me anything!
        `;
        
        this.addMessage(welcomeMessage, 'bot');
    }

    addMessage(text, sender) {
        const messageDiv = document.createElement('div');
        messageDiv.className = `message message-${sender}`;
        
        if (sender === 'bot' && text.includes('```')) {
            // Handle code blocks in messages
            const parts = text.split('```');
            let html = '';
            
            for (let i = 0; i < parts.length; i++) {
                if (i % 2 === 0) {
                    // Regular text
                    html += parts[i].replace(/\n/g, '<br>');
                } else {
                    // Code block
                    html += `<div class="message-code">${this.escapeHtml(parts[i])}</div>`;
                }
            }
            
            messageDiv.innerHTML = html;
        } else {
            messageDiv.textContent = text;
        }
        
        this.messagesContainer.appendChild(messageDiv);
        this.messagesContainer.scrollTop = this.messagesContainer.scrollHeight;
        
        // Add to messages history
        this.messages.push({ text, sender, timestamp: new Date() });
    }

    escapeHtml(text) {
        const div = document.createElement('div');
        div.textContent = text;
        return div.innerHTML;
    }

    showTypingIndicator() {
        const typingDiv = document.createElement('div');
        typingDiv.className = 'typing-indicator';
        typingDiv.innerHTML = `
            AI is thinking
            <span class="typing-dot"></span>
            <span class="typing-dot"></span>
            <span class="typing-dot"></span>
        `;
        this.messagesContainer.appendChild(typingDiv);
        this.messagesContainer.scrollTop = this.messagesContainer.scrollHeight;
        return typingDiv;
    }

    hideTypingIndicator(typingDiv) {
        if (typingDiv && typingDiv.parentNode) {
            typingDiv.parentNode.removeChild(typingDiv);
        }
    }

    async sendMessage() {
        const message = this.input.value.trim();
        if (!message) return;

        this.input.value = '';
        this.autoResize();
        
        this.addMessage(message, 'user');
        
        const typingIndicator = this.showTypingIndicator();
        
        try {
            const response = await this.getAIResponse(message);
            this.hideTypingIndicator(typingIndicator);
            this.addMessage(response, 'bot');
        } catch (error) {
            this.hideTypingIndicator(typingIndicator);
            this.addMessage('Sorry, I encountered an error. Please try again.', 'bot');
            console.error('Chatbot error:', error);
        }
    }

    async getAIResponse(message) {
        // Simulate API call delay
        await new Promise(resolve => setTimeout(resolve, 1000 + Math.random() * 2000));
        
        // Mock AI responses based on message content
        const lowerMessage = message.toLowerCase();
        
        if (lowerMessage.includes('format') || lowerMessage.includes('beautify')) {
            return this.handleFormatRequest(message);
        } else if (lowerMessage.includes('explain') || lowerMessage.includes('what does')) {
            return this.handleExplainRequest(message);
        } else if (lowerMessage.includes('optimize') || lowerMessage.includes('refactor')) {
            return this.handleOptimizeRequest(message);
        } else if (lowerMessage.includes('debug') || lowerMessage.includes('error')) {
            return this.handleDebugRequest(message);
        } else if (lowerMessage.includes('hello') || lowerMessage.includes('hi')) {
            return "Hello! How can I assist you with your code today?";
        } else {
            return "I understand you're looking for coding assistance. Could you provide more details or try one of the quick actions above?";
        }
    }

    handleFormatRequest(message) {
        return `I can help you format code! Here's an example of well-formatted code:

\`\`\`
public class FormattedExample {
    public static void main(String[] args) {
        // Proper indentation and spacing
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.println("Even: " + i);
            } else {
                System.out.println("Odd: " + i);
            }
        }
    }
}
\`\`\`

Paste your code and I'll format it for you!`;
    }

    handleExplainRequest(message) {
        return `I'd be happy to explain code! For example:

\`\`\`
public int factorial(int n) {
    if (n <= 1) return 1;
    return n * factorial(n - 1);
}
\`\`\`

This is a recursive factorial function. It:
• Takes an integer n as input
• Returns 1 if n is 0 or 1 (base case)
• Otherwise, returns n multiplied by factorial of n-1
• Calls itself recursively until base case is reached

Share your code and I'll explain it in detail!`;
    }

    handleOptimizeRequest(message) {
        return `Here's an optimization example:

**Before:**
\`\`\`
for (int i = 0; i < list.size(); i++) {
    String item = list.get(i);
    System.out.println(item);
}
\`\`\`

**After:**
\`\`\`
for (String item : list) {
    System.out.println(item);
}
\`\`\`

The enhanced for loop is more readable and efficient!

Share your code for optimization suggestions.`;
    }

    handleDebugRequest(message) {
        return `Common debugging tips:

1. **Check for null pointers**
2. **Validate input parameters**
3. **Use try-catch blocks**
4. **Add logging statements**
5. **Test with different inputs**

Example error handling:
\`\`\`
try {
    File file = new File("example.txt");
    Scanner scanner = new Scanner(file);
} catch (FileNotFoundException e) {
    System.out.println("File not found: " + e.getMessage());
}
\`\`\`

What specific issue are you facing?`;
    }

    handleQuickAction(action) {
        const actions = {
            format: "Please paste the code you'd like me to format. I'll make it clean and readable!",
            explain: "Share the code you want explained. I'll break it down step by step.",
            optimize: "Paste your code for optimization suggestions. I'll help make it more efficient!",
            debug: "What code are you having trouble with? I'll help you debug it."
        };
        
        this.addMessage(actions[action], 'bot');
    }
}

// Initialize chatbot when page loads
document.addEventListener('DOMContentLoaded', () => {
    new AIChatbot();
});