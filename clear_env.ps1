[CmdletBinding(SupportsShouldProcess = $true, ConfirmImpact = 'Low')]
param($localEnvFile = ".\.env")

#return if no env file
if (!( Test-Path $localEnvFile)) {
    Throw "could not open $localEnvFile"
}

#read the local env file
$content = Get-Content $localEnvFile -ErrorAction Stop
Write-Verbose "Parsed .env file"

#load the content to environment
foreach ($line in $content) {
    if ($line.StartsWith("#")) { continue };
    if ($line.Trim()) {
        $line = $line.Replace("`"","")
        $kvp = $line -split "=",2
        #        if ($PSCmdlet.ShouldProcess("$($kvp[0])", "set value $($kvp[1])")) {
        [System.Environment]::SetEnvironmentVariable($kvp[0].Trim(), $null,
                [System.EnvironmentVariableTarget]::User)
        #        }
    }
}