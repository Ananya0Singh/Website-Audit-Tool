import { useState } from 'react'
import AuditForm from './components/AuditForm.jsx'
import AuditResult from './components/AuditResult.jsx'
import Footer from './components/Footer.jsx'
import { auditUrl } from './api/auditApi.js'
import './App.css'

function App() {
  const [result, setResult] = useState(null)
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)

  const handleAnalyze = async (url) => {
    setError('')
    setResult(null)

    if (!url || !url.trim()) {
      setError('Please enter a URL to analyze')
      return
    }

    setLoading(true)
    try {
      const data = await auditUrl(url.trim())
      setResult(data)
    } catch (err) {
      const message =
        err.response?.data?.message ||
        'Unable to reach the server. Please try again'
      setError(message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="app-shell">
      <main className="app-main">
        <header className="app-header">
          <h1>
            Page<span>Pulse</span>
          </h1>
          <p>Instant insights into any webpage's health and structure</p>
        </header>

        <AuditForm onAnalyze={handleAnalyze} loading={loading} />

        {loading && (
          <div className="loading-indicator">
            <div className="spinner" />
            <span>Fetching and analyzing the page…</span>
          </div>
        )}

        {error && !loading && <div className="error-message">{error}</div>}

        {result && !loading && <AuditResult result={result} />}
      </main>

      <Footer />
    </div>
  )
}

export default App
