import { useState } from 'react'

function AuditForm({ onAnalyze, loading }) {
  const [url, setUrl] = useState('')

  const handleSubmit = (e) => {
    e.preventDefault()
    onAnalyze(url)
  }

  return (
    <form className="audit-form" onSubmit={handleSubmit}>
      <input
        type="text"
        className="audit-input"
        placeholder="Enter a website URL, e.g. https://example.com"
        value={url}
        onChange={(e) => setUrl(e.target.value)}
        disabled={loading}
      />
      <button type="submit" className="audit-button" disabled={loading}>
        {loading ? 'Analyzing…' : 'Analyze'}
      </button>
    </form>
  )
}

export default AuditForm
