import kotlin.random.Random

// Define the data class
data class QuoteOfTheDay(
    val author: String,
    val quote: String
) {
    companion object {
        // Define a list of 100 best quotes
        private val bestQuotes = listOf(
            QuoteOfTheDay("Albert Einstein", "Imagination is more important than knowledge."),
            QuoteOfTheDay("Maya Angelou", "If you don't like something, change it."),
            QuoteOfTheDay("Steve Jobs", "Innovation distinguishes a leader from a follower."),
            QuoteOfTheDay("Oscar Wilde", "Be yourself; everyone else is already taken."),
            QuoteOfTheDay("Buddha", "The mind is everything. What you think, you become."),
            QuoteOfTheDay("Winston Churchill", "Success is not final, failure is not fatal."),
            QuoteOfTheDay("Confucius", "It does not matter how slowly you go as long as you do not stop."),
            QuoteOfTheDay("Eleanor Roosevelt", "The future belongs to those who believe."),
            QuoteOfTheDay("Mark Twain", "The secret of getting ahead is getting started."),
            QuoteOfTheDay("Nelson Mandela", "It always seems impossible until it's done."),
            QuoteOfTheDay("Leonardo da Vinci", "Simplicity is the ultimate sophistication."),
            QuoteOfTheDay("Henry Ford", "Whether you think you can or you think you can't, you're right."),
            QuoteOfTheDay("Benjamin Franklin", "By failing to prepare, you are preparing to fail."),
            QuoteOfTheDay("Aristotle", "We are what we repeatedly do. Excellence is a habit."),
            QuoteOfTheDay("John Lennon", "Life is what happens to you while you're busy making plans."),
            QuoteOfTheDay("William Shakespeare", "To be, or not to be, that is the question."),
            QuoteOfTheDay("Helen Keller", "The best and most beautiful things must be felt with the heart."),
            QuoteOfTheDay("Dalai Lama", "Happiness is not something ready-made. It comes from your actions."),
            QuoteOfTheDay("Dr. Seuss", "Don't cry because it's over, smile because it happened."),
            QuoteOfTheDay("Ralph Waldo Emerson", "The only way to have a friend is to be one."),
            QuoteOfTheDay("Mahatma Gandhi", "Be the change that you wish to see in the world."),
            QuoteOfTheDay("Henry David Thoreau", "Go confidently in the direction of your dreams."),
            QuoteOfTheDay("Anne Frank", "How wonderful it is that nobody need wait a single moment."),
            QuoteOfTheDay("George Bernard Shaw", "Life isn't about finding yourself. It's about creating yourself."),
            QuoteOfTheDay("Friedrich Nietzsche", "That which does not kill us makes us stronger."),
            QuoteOfTheDay("Voltaire", "I disapprove of what you say, but will defend to the death your right to say it."),
            QuoteOfTheDay("J.K. Rowling", "It is our choices that show what we truly are."),
            QuoteOfTheDay("Martin Luther King Jr.", "Injustice anywhere is a threat to justice everywhere."),
            QuoteOfTheDay("John F. Kennedy", "Ask not what your country can do for you."),
            QuoteOfTheDay("Albert Camus", "In the depth of winter, I found an invincible summer."),
            QuoteOfTheDay("Bob Marley", "Love the life you live. Live the life you love."),
            QuoteOfTheDay("Charles Dickens", "It was the best of times, it was the worst of times."),
            QuoteOfTheDay("Jane Austen", "There is no charm equal to tenderness of heart."),
            QuoteOfTheDay("Victor Hugo", "Even the darkest night will end and the sun will rise."),
            QuoteOfTheDay("Anne Lamott", "Hope begins in the dark, the stubborn hope that if you just show up."),
            QuoteOfTheDay("Tony Robbins", "The only limit to your impact is your imagination and commitment."),
            QuoteOfTheDay("Zig Ziglar", "You don’t have to be great to start, but you have to start to be great."),
            QuoteOfTheDay("Brene Brown", "Vulnerability is the birthplace of innovation, creativity, and change."),
            QuoteOfTheDay("Oprah Winfrey", "The more you praise and celebrate your life, the more there is in life to celebrate."),
            QuoteOfTheDay("Aesop", "No act of kindness, no matter how small, is ever wasted."),
            QuoteOfTheDay("Confucius", "It does not matter how slowly you go as long as you do not stop."),
            QuoteOfTheDay("Marcus Aurelius", "The happiness of your life depends upon the quality of your thoughts."),
            QuoteOfTheDay("Plato", "The beginning is the most important part of the work."),
            QuoteOfTheDay("Ralph Waldo Emerson", "The only person you are destined to become is the person you decide to be."),
            QuoteOfTheDay("William Blake", "The road of excess leads to the palace of wisdom."),
            QuoteOfTheDay("William Arthur Ward", "Opportunities are like sunrises. If you wait too long, you miss them.")
        )


        fun getRandomQuote(): QuoteOfTheDay {
            return bestQuotes.random()
        }
    }
}
