function getStatusClass(status) {
  if (status >= 200 && status < 300) return 'status-good'
  if (status >= 300 && status < 400) return 'status-warn'
  return 'status-bad'
}

function AuditResult({ result }) {
  if (!result) return null

  const {
    status,
    responseTime,
    title,
    metaDescription,
    h1Count,
    imagesWithoutAlt,
    wordCount,
  } = result

  const stats = [
    { label: 'HTTP Status', value: status, badge: getStatusClass(status) },
    { label: 'Response Time', value: `${responseTime} ms` },
    { label: 'H1 Tags', value: h1Count },
    { label: 'Images Missing Alt', value: imagesWithoutAlt, warn: imagesWithoutAlt > 0 },
    { label: 'Word Count', value: wordCount },
  ]

  return (
    <div className="result-wrapper">
      <div className="result-summary">
        <div className="summary-field">
          <span className="summary-label">Page Title</span>
          <span className="summary-value">{title || '—'}</span>
        </div>
        <div className="summary-field">
          <span className="summary-label">Meta Description</span>
          <span className="summary-value">{metaDescription || '—'}</span>
        </div>
      </div>

      <div className="stats-grid">
        {stats.map((stat) => (
          <div className="stat-card" key={stat.label}>
            <span className="stat-label">{stat.label}</span>
            <span
              className={
                'stat-value' +
                (stat.badge ? ` ${stat.badge}` : '') +
                (stat.warn ? ' status-warn' : '')
              }
            >
              {stat.value}
            </span>
          </div>
        ))}
      </div>
    </div>
  )
}

export default AuditResult
